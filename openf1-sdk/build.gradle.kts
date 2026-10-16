import org.gradle.plugins.signing.SigningExtension

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.detekt)
    id("maven-publish")
    id("signing")
}

group = "io.github.slashgil.openf1"
version = "1.0.0-SNAPSHOT"

kotlin {
    jvm {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    js(IR) {
        browser()
        nodejs()
    }

    @OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.ktor.client.mock)
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}

android {
    namespace = "io.github.slashgil.openf1"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    lint {
        abortOnError = true
        warningsAsErrors = false
    }
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("OpenF1 Kotlin Multiplatform SDK")
            description.set("Strongly-typed Kotlin Multiplatform wrapper for the OpenF1 REST API.")
            url.set("https://github.com/slashgil/open-f1")

            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }

            developers {
                developer {
                    id.set("slashgil")
                    name.set("SlashGil")
                    url.set("https://github.com/slashgil")
                }
            }

            scm {
                connection.set("scm:git:git://github.com/slashgil/open-f1.git")
                developerConnection.set("scm:git:ssh://github.com/slashgil/open-f1.git")
                url.set("https://github.com/slashgil/open-f1")
            }
        }
    }

    repositories {
        maven {
            name = "SonatypeCentral"
            url = uri("https://central.sonatype.com/api/v1/publisher/deployments/upload")
            credentials {
                username = System.getenv("SONATYPE_TOKEN_USERNAME")
                password = System.getenv("SONATYPE_TOKEN_PASSWORD")
            }
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/slashgil/open-f1")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: providers.gradleProperty("gpr.user").orNull
                password = System.getenv("GITHUB_TOKEN") ?: providers.gradleProperty("gpr.key").orNull
            }
        }
    }
}

configure<SigningExtension> {
    val secretKey = System.getenv("GPG_PRIVATE_KEY")
    val passphrase = System.getenv("GPG_PASSPHRASE")
    if (!secretKey.isNullOrEmpty()) {
        useInMemoryPgpKeys(secretKey, passphrase)
        sign(publishing.publications)
    }
}

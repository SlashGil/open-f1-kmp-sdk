# OpenF1 Kotlin Multiplatform SDK

A strongly-typed, pure data layer Kotlin Multiplatform (KMP) SDK for the [OpenF1 REST API](https://api.openf1.org/v1/).

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.10-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Ktor](https://img.shields.io/badge/Ktor-3.1.1-orange.svg)](https://ktor.io)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

---

## 🚀 Supported Platforms

- **Android** (minSdk 21+)
- **JVM** (Java 11+)
- **iOS** (`iosX64`, `iosArm64`, `iosSimulatorArm64`)
- **JS** (Browser & Node.js)
- **Wasm JS** (Browser & Node.js)

---

## 📡 Supported OpenF1 Endpoints

This SDK provides strongly-typed models and suspend functions for the OpenF1 v1 API endpoints:

| Endpoint | Method | SDK Function | Description |
| :--- | :--- | :--- | :--- |
| `/drivers` | `GET` | `getDrivers(...)` | Driver profiles, acronyms, team colors, and headshot URLs. |
| `/laps` | `GET` | `getLaps(...)` | Detailed lap timing, sector durations, speeds, and segment data. |
| `/pit_stops` | `GET` | `getPitStops(...)` | Pit stop events, stop numbers, and pit duration. |
| `/stints` | `GET` | `getStints(...)` | Tyre stints, compounds (SOFT, MEDIUM, HARD), and tyre age. |
| `/team_radio` | `GET` | `getTeamRadio(...)` | Team radio audio recording URLs and metadata. |

---

## 📦 Installation

Add the dependency to your project's `build.gradle.kts`:

### Kotlin Multiplatform / Android

```kotlin
repositories {
    mavenCentral()
    // Or mavenLocal() if using a local snapshot
    mavenLocal()
}

dependencies {
    implementation("org.openf1:openf1-sdk:1.0.0-SNAPSHOT")
}
```

---

## 💡 Quickstart & Code Examples

### Initializing the Client

```kotlin
import org.openf1.client.OpenF1Client

// Create a client with default settings
val client = OpenF1Client()
```

---

### 1. Fetch Drivers (`GET /drivers`)

```kotlin
// Get drivers for session 9158 (e.g. Hamilton, Verstappen, etc.)
val drivers = client.getDrivers(sessionKey = 9158)

drivers.forEach { driver ->
    println("#${driver.driverNumber} - ${driver.fullName} (${driver.teamName})")
}
```

---

### 2. Fetch Lap Times (`GET /laps`)

```kotlin
// Get lap timing data for a specific driver and session
val laps = client.getLaps(
    sessionKey = 9158,
    driverNumber = 1,
    lapNumber = 12
)

laps.forEach { lap ->
    println("Lap ${lap.lapNumber}: ${lap.lapDuration}s (S1: ${lap.durationSector1}s, S2: ${lap.durationSector2}s, S3: ${lap.durationSector3}s)")
}
```

---

### 3. Fetch Pit Stops (`GET /pit_stops`)

```kotlin
// Get pit stop events
val pitStops = client.getPitStops(sessionKey = 9158)

pitStops.forEach { pit ->
    println("Driver #${pit.driverNumber} - Stop #${pit.stopNumber} on lap ${pit.lapNumber}: ${pit.pitDuration}s")
}
```

---

### 4. Fetch Tyre Stints (`GET /stints`)

```kotlin
// Get stint and tyre compound information
val stints = client.getStints(sessionKey = 9158)

stints.forEach { stint ->
    println("Driver #${stint.driverNumber} - Stint ${stint.stintNumber}: ${stint.compound} (Laps ${stint.lapStart} to ${stint.lapEnd})")
}
```

---

### 5. Fetch Team Radio (`GET /team_radio`)

```kotlin
// Get team radio recording URLs
val teamRadioEntries = client.getTeamRadio(sessionKey = 9158, driverNumber = 55)

teamRadioEntries.forEach { radio ->
    println("Driver #${radio.driverNumber} Radio URL: ${radio.recordingUrl}")
}
```

---

### Error Handling

All requests throw an `OpenF1Exception` in case of HTTP error codes or network issues:

```kotlin
import org.openf1.exception.OpenF1Exception

try {
    val drivers = client.getDrivers(sessionKey = 9158)
} catch (e: OpenF1Exception) {
    println("API Error [${e.statusCode}]: ${e.message}")
}
```

---

### Closing the Client

`OpenF1Client` implements `AutoCloseable`, so you can close it when finished or scope it using `.use { }`:

```kotlin
client.close()
```

---

## 🛠️ Building & Publishing

### Run Unit Tests
```bash
./gradlew openf1-sdk:jvmTest
```

### Publish to Maven Local
```bash
./gradlew publishToMavenLocal
```

---

## 📄 License

This library is released under the [MIT License](LICENSE).

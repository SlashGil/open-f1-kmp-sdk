# OpenF1 Kotlin Multiplatform SDK

A strongly-typed, pure data layer Kotlin Multiplatform (KMP) SDK for the complete [OpenF1 REST API](https://openf1.org/docs/#api-endpoints).

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

## 📡 Official OpenF1 API Endpoints Covered

This SDK provides 100% complete coverage for all official endpoints documented in [openf1.org/docs/#api-endpoints](https://openf1.org/docs/#api-endpoints):

| # | Endpoint | Method | SDK Function (`org.openf1.contract.OpenF1Client`) | Description |
| :-: | :--- | :-: | :--- | :--- |
| **1** | `/car_data` | `GET` | `getCarData(...)` | Live car telemetry (speed, RPM, gear, throttle, brake, DRS) at ~3.7 Hz. |
| **2** | `/championship_drivers` | `GET` | `getChampionshipDrivers(...)` | Drivers' championship standings during or after race sessions. |
| **3** | `/championship_teams` | `GET` | `getChampionshipTeams(...)` | Teams' championship standings during or after race sessions. |
| **4** | `/drivers` | `GET` | `getDrivers(...)` | Driver profiles, acronyms, team names, colors, and headshots. |
| **5** | `/intervals` | `GET` | `getIntervals(...)` | Real-time gaps and interval times between drivers. |
| **6** | `/laps` | `GET` | `getLaps(...)` | Sector times, lap durations, speeds, and pit-out status. |
| **7** | `/location` | `GET` | `getLocation(...)` | 3D Cartesian spatial coordinates (X, Y, Z) of cars on track. |
| **8** | `/meetings` | `GET` | `getMeetings(...)` | Grand Prix / testing event weekend metadata, locations, and circuit keys. |
| **9** | `/overtakes` | `GET` | `getOvertakes(...)` | Position changes and overtake records during races. |
| **10** | `/pit` | `GET` | `getPit(...)` | Pit stop events, stop durations, and pit lane durations. |
| **11** | `/pit_stops` | `GET` | `getPitStops(...)` | Convenience alias for pit stop events. |
| **12** | `/position` | `GET` | `getPosition(...)` | Driver position history throughout a session. |
| **13** | `/race_control` | `GET` | `getRaceControl(...)` | Flags, safety car messages, investigations, and track status. |
| **14** | `/sessions` | `GET` | `getSessions(...)` | Session metadata (Practice, Qualifying, Sprint, Race). |
| **15** | `/stints` | `GET` | `getStints(...)` | Tyre stints, compounds (SOFT, MEDIUM, HARD), and tyre age. |
| **16** | `/team_radio` | `GET` | `getTeamRadio(...)` | Team radio audio recording URLs and timestamps. |
| **17** | `/weather` | `GET` | `getWeather(...)` | Air/track temperature, humidity, wind, and rainfall. |

> 💡 **Tip:** `sessionKey` and `meetingKey` parameters accept both `Int` keys (e.g. `9158`) and the `"latest"` string keyword!

---

## 🏛 Architecture & Packages

```
org.openf1
├── contract/     # API interface contracts (OpenF1Client)
├── impl/         # Concrete client implementations (DefaultOpenF1Client)
├── model/        # @Serializable DTO data classes (CarData, Driver, Lap, etc.)
└── exception/    # Custom exceptions (OpenF1Exception)
```

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

## 💡 Quickstart Examples

```kotlin
import org.openf1.contract.OpenF1Client

val client = OpenF1Client()

// 1. Fetch Drivers using "latest" session keyword
val drivers = client.getDrivers(sessionKey = "latest")

// 2. Fetch Championship Standings
val standings = client.getChampionshipDrivers(sessionKey = "latest")

// 3. Fetch Overtakes
val overtakes = client.getOvertakes(sessionKey = "latest")

// 4. Fetch Car Telemetry (Speed, RPM, Gear)
val carData = client.getCarData(sessionKey = 9158, driverNumber = 1)

// 5. Fetch Weather Data
val weather = client.getWeather(sessionKey = 9158)

// Close client when finished
client.close()
```

---

## 🛠️ Building & Testing

### Run Unit Tests
```bash
./gradlew openf1-sdk:jvmTest
```

### Publish Locally
```bash
./gradlew publishToMavenLocal
```

---

## 📄 License

This library is released under the [MIT License](LICENSE).

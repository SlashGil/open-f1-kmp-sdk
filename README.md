# OpenF1 Kotlin Multiplatform SDK

A strongly-typed, pure data layer Kotlin Multiplatform (KMP) SDK for the complete [OpenF1 REST API](https://openf1.org/docs/#api-endpoints).

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.10-blue.svg?logo=kotlin)](https://kotlinlang.org)
[![Ktor](https://img.shields.io/badge/Ktor-3.1.1-orange.svg)](https://ktor.io)
[![JitPack](https://jitpack.io/v/SlashGil/open-f1-kmp-sdk.svg)](https://jitpack.io/#SlashGil/open-f1-kmp-sdk)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

---

## 🚀 Supported Platforms

- **Android** (minSdk 21+)
- **JVM** (Java 11+)
- **iOS** (`iosX64`, `iosArm64`, `iosSimulatorArm64`)
- **JS** (Browser & Node.js)
- **Wasm JS** (Browser & Node.js)

---

## 📦 Installation

Add JitPack repository and dependency to your project's `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.slashgil.open-f1:openf1-sdk:v1.0.1")
}
```

---

## 📡 Official OpenF1 API Endpoints Covered (17/17)

This SDK provides 100% complete coverage for all official endpoints documented in [openf1.org/docs/#api-endpoints](https://openf1.org/docs/#api-endpoints):

| # | Endpoint | Method | SDK Function (`io.github.slashgil.openf1.contract.OpenF1Client`) | Description |
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

---

## 📖 Full Usage Guide

### 1. Client Initialization

```kotlin
import io.github.slashgil.openf1.contract.OpenF1Client

// Standard initialization with default settings
val client = OpenF1Client()

// Or with custom settings (custom Ktor HttpClient, timeouts, or base URL)
val customClient = OpenF1Client(
    baseUrl = "https://api.openf1.org/v1",
    httpClient = OpenF1Client.createDefaultHttpClient()
)
```

#### Dependency Injection (DI) Support

The SDK provides both a framework-agnostic pure Kotlin container and a Koin Multiplatform module out of the box:

##### Option A: Framework-Agnostic Container (`OpenF1Sdk`)
```kotlin
import com.slashgil.openf1.di.OpenF1Sdk
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier

// Instantiate pure Kotlin DI container
val sdk = OpenF1Sdk.create()

// Access Repositories or Use Cases
val leaderboard = sdk.getSessionLeaderboardUseCase(SessionSpecifier.Latest)
val drivers = sdk.driverRepository.getDrivers(sessionKey = SessionSpecifier.Latest)

// Close underlying resources when finished
sdk.close()
```

##### Option B: Koin Multiplatform Module (`openF1Module`)
```kotlin
import com.slashgil.openf1.di.openF1Module
import org.koin.core.context.startKoin

startKoin {
    modules(openF1Module())
}

// Inject in ViewModel / Presenter
class RaceViewModel(
    private val getSessionLeaderboardUseCase: GetSessionLeaderboardUseCase
)
```

---

### 2. Discovering Sessions & Meetings (`session_key` / `meeting_key`)

OpenF1 API queries use `sessionKey` or `meetingKey`. You can query them or use the `"latest"` string keyword:

#### Option A: Use the `"latest"` Keyword
```kotlin
// Fetch data for the current or most recent session without knowing the ID
val currentDrivers = client.getDrivers(sessionKey = "latest")
val currentSession = client.getSessions(sessionKey = "latest")
```

#### Option B: Search Sessions by Year & Type (`GET /sessions`)
```kotlin
// Find all 2024 Race sessions
val sessions = client.getSessions(year = 2024, sessionType = "Race")

sessions.forEach { session ->
    println("Session Key: ${session.sessionKey} | ${session.location} - ${session.sessionName} (${session.dateStart})")
}
```

#### Option C: Search Meetings by Year & Country (`GET /meetings`)
```kotlin
// Find Grand Prix meetings in 2024
val meetings = client.getMeetings(year = 2024)

meetings.forEach { meeting ->
    println("Meeting Key: ${meeting.meetingKey} | ${meeting.meetingName} (${meeting.countryName})")
}
```

---

### 3. Drivers & Championship Standings

```kotlin
// Fetch drivers for a session
val drivers = client.getDrivers(sessionKey = "latest")
drivers.forEach { driver ->
    println("#${driver.driverNumber}: ${driver.fullName} (${driver.teamName})")
}

// Fetch Drivers Championship Standings
val driverStandings = client.getChampionshipDrivers(sessionKey = "latest")
driverStandings.forEach { standing ->
    println("P${standing.positionCurrent}: Driver #${standing.driverNumber} - ${standing.pointsCurrent} pts")
}

// Fetch Teams Championship Standings
val teamStandings = client.getChampionshipTeams(sessionKey = "latest")
teamStandings.forEach { team ->
    println("P${team.positionCurrent}: ${team.teamName} - ${team.pointsCurrent} pts")
}
```

---

### 4. Live Telemetry & 3D Spatial Location

```kotlin
// Fetch car telemetry (speed, RPM, gear, throttle, brake, DRS) at ~3.7 Hz
val telemetry = client.getCarData(sessionKey = "latest", driverNumber = 1)
telemetry.take(5).forEach { data ->
    println("Speed: ${data.speed} km/h | RPM: ${data.rpm} | Gear: ${data.gear} | Throttle: ${data.throttle}% | DRS: ${data.drs}")
}

// Fetch 3D spatial track coordinates (X, Y, Z)
val locationData = client.getLocation(sessionKey = "latest", driverNumber = 1)
locationData.take(5).forEach { loc ->
    println("Coordinates: X=${loc.x}, Y=${loc.y}, Z=${loc.z}")
}
```

---

### 5. Lap Times, Intervals & Overtakes

```kotlin
// Fetch lap details (sector times & speeds)
val laps = client.getLaps(sessionKey = "latest", driverNumber = 44)
laps.forEach { lap ->
    println("Lap ${lap.lapNumber}: ${lap.lapDuration}s | S1: ${lap.durationSector1}s | S2: ${lap.durationSector2}s | S3: ${lap.durationSector3}s")
}

// Fetch live intervals & gap to leader
val intervals = client.getIntervals(sessionKey = "latest")
intervals.forEach { interval ->
    println("Driver #${interval.driverNumber} | Gap to Leader: ${interval.gapToLeader}s | Interval: ${interval.interval}s")
}

// Fetch race overtakes
val overtakes = client.getOvertakes(sessionKey = "latest")
overtakes.forEach { ot ->
    println("Driver #${ot.overtakingDriverNumber} overtook Driver #${ot.overtakenDriverNumber} for P${ot.position}")
}
```

---

### 6. Pit Stops & Tyre Strategy Stints

```kotlin
// Fetch pit stop durations
val pitStops = client.getPit(sessionKey = "latest")
pitStops.forEach { pit ->
    println("Driver #${pit.driverNumber} - Lap ${pit.lapNumber} | Stop Duration: ${pit.stopDuration}s | Pit Lane Duration: ${pit.laneDuration}s")
}

// Fetch tyre compound stints
val stints = client.getStints(sessionKey = "latest")
stints.forEach { stint ->
    println("Driver #${stint.driverNumber} | Stint #${stint.stintNumber}: ${stint.compound} (Laps ${stint.lapStart} to ${stint.lapEnd}, Tyre Age: ${stint.tyreAgeAtStart})")
}
```

---

### 7. Race Control, Team Radio & Weather

```kotlin
// Fetch Race Control messages & flags
val raceControl = client.getRaceControl(sessionKey = "latest")
raceControl.forEach { msg ->
    println("[${msg.category}] Flag: ${msg.flag} | Message: ${msg.message}")
}

// Fetch Team Radio audio URLs
val radioEntries = client.getTeamRadio(sessionKey = "latest", driverNumber = 55)
radioEntries.forEach { radio ->
    println("Driver #${radio.driverNumber} Audio URL: ${radio.recordingUrl}")
}

// Fetch track weather conditions
val weather = client.getWeather(sessionKey = "latest")
weather.take(1).forEach { w ->
    println("Air Temp: ${w.airTemperature}°C | Track Temp: ${w.trackTemperature}°C | Humidity: ${w.humidity}% | Rainfall: ${w.rainfall}")
}
```

---

### 8. Error Handling

API errors or status issues throw `OpenF1Exception`:

```kotlin
import io.github.slashgil.openf1.exception.OpenF1Exception

try {
    val drivers = client.getDrivers(sessionKey = 999999)
} catch (e: OpenF1Exception) {
    println("API Exception [HTTP ${e.statusCode}]: ${e.message}")
}
```

---

### 9. Resource Management

`OpenF1Client` implements `AutoCloseable`. You can close it manually or scope it with `.use { }`:

```kotlin
// Option A: Explicit close
val client = OpenF1Client()
// ... use client ...
client.close()

// Option B: Idiomatic Kotlin .use block
OpenF1Client().use { client ->
    val drivers = client.getDrivers(sessionKey = "latest")
    // Client closes automatically after block
}
```

---

## 🏛 Architecture & Packages

```
io.github.slashgil.openf1
├── contract/     # API interface contracts (OpenF1Client)
├── impl/         # Concrete client implementations (DefaultOpenF1Client)
├── model/        # @Serializable DTO data classes (CarData, Driver, Lap, etc.)
└── exception/    # Custom exceptions (OpenF1Exception)
```

---

## 🛠️ Building & Testing

### Run Static Analysis & Unit Tests
```bash
./gradlew openf1-sdk:detekt openf1-sdk:lintRelease openf1-sdk:jvmTest
```

### Consumer Sample Verification (:sample)
The project includes a `:sample` KMP consumer module validating cross-platform compilation in `commonMain` across Android, JVM, iOS, JS, and Wasm JS targets:
```bash
./gradlew sample:compileKotlinJvm sample:compileKotlinJs sample:compileKotlinWasmJs
```

### Publish Locally
```bash
./gradlew publishToMavenLocal
```

---

## 📄 License

This library is released under the [MIT License](LICENSE).

package com.slashgil.openf1.data.mapper

import com.slashgil.openf1.domain.model.CarLocation
import com.slashgil.openf1.domain.model.CarTelemetry
import com.slashgil.openf1.domain.model.DriverInterval
import com.slashgil.openf1.domain.model.DriverPosition
import com.slashgil.openf1.domain.model.DriverProfile
import com.slashgil.openf1.domain.model.DriverStanding
import com.slashgil.openf1.domain.model.GrandPrixMeeting
import com.slashgil.openf1.domain.model.LapTiming
import com.slashgil.openf1.domain.model.OvertakeEvent
import com.slashgil.openf1.domain.model.PitLaneEntry
import com.slashgil.openf1.domain.model.PitStop
import com.slashgil.openf1.domain.model.RaceControlMessage
import com.slashgil.openf1.domain.model.RaceSession
import com.slashgil.openf1.domain.model.TeamRadioMessage
import com.slashgil.openf1.domain.model.TeamStanding
import com.slashgil.openf1.domain.model.TrackWeather
import com.slashgil.openf1.domain.model.TyreStint
import com.slashgil.openf1.domain.model.enums.RaceControlCategory
import com.slashgil.openf1.domain.model.enums.RaceControlFlag
import com.slashgil.openf1.domain.model.enums.SessionType
import com.slashgil.openf1.domain.model.enums.TyreCompound
import com.slashgil.openf1.model.CarData
import com.slashgil.openf1.model.ChampionshipDriver
import com.slashgil.openf1.model.ChampionshipTeam
import com.slashgil.openf1.model.Driver
import com.slashgil.openf1.model.Interval
import com.slashgil.openf1.model.Lap
import com.slashgil.openf1.model.Location
import com.slashgil.openf1.model.Meeting
import com.slashgil.openf1.model.Overtake
import com.slashgil.openf1.model.Pit
import com.slashgil.openf1.model.PitStop as PitStopDto
import com.slashgil.openf1.model.Position
import com.slashgil.openf1.model.RaceControl
import com.slashgil.openf1.model.Session
import com.slashgil.openf1.model.Stint
import com.slashgil.openf1.model.TeamRadio
import com.slashgil.openf1.model.Weather
import kotlinx.datetime.Instant

internal fun String?.toInstantOrNull(): Instant? = if (this.isNullOrBlank()) null else try {
    Instant.parse(this)
} catch (_: Exception) {
    null
}

fun CarData.toDomain(): CarTelemetry = CarTelemetry(
    driverNumber = driverNumber,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    brake = brake,
    drs = drs,
    gear = gear,
    nGear = nGear,
    rpm = rpm,
    speed = speed,
    throttle = throttle,
    date = date.toInstantOrNull()
)

fun ChampionshipDriver.toDomain(): DriverStanding = DriverStanding(
    driverNumber = driverNumber,
    meetingKey = meetingKey,
    sessionKey = sessionKey,
    pointsCurrent = pointsCurrent,
    pointsStart = pointsStart,
    positionCurrent = positionCurrent,
    positionStart = positionStart
)

fun ChampionshipTeam.toDomain(): TeamStanding = TeamStanding(
    teamName = teamName,
    meetingKey = meetingKey,
    sessionKey = sessionKey,
    pointsCurrent = pointsCurrent,
    pointsStart = pointsStart,
    positionCurrent = positionCurrent,
    positionStart = positionStart
)

fun Driver.toDomain(): DriverProfile = DriverProfile(
    driverNumber = driverNumber,
    fullName = fullName,
    firstName = firstName,
    lastName = lastName,
    broadcastName = broadcastName,
    nameAcronym = nameAcronym,
    countryCode = countryCode,
    teamName = teamName,
    teamColour = teamColour,
    headshotUrl = headshotUrl,
    sessionKey = sessionKey,
    meetingKey = meetingKey
)

fun Interval.toDomain(): DriverInterval = DriverInterval(
    driverNumber = driverNumber,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    gapToLeader = gapToLeader,
    interval = interval,
    date = date.toInstantOrNull()
)

fun Lap.toDomain(): LapTiming = LapTiming(
    driverNumber = driverNumber,
    lapNumber = lapNumber,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    lapDuration = lapDuration,
    durationSector1 = durationSector1,
    durationSector2 = durationSector2,
    durationSector3 = durationSector3,
    i1Speed = i1Speed,
    i2Speed = i2Speed,
    stSpeed = stSpeed,
    isPitOutLap = isPitOutLap,
    segmentsSector1 = segmentsSector1,
    segmentsSector2 = segmentsSector2,
    segmentsSector3 = segmentsSector3,
    dateStart = dateStart.toInstantOrNull()
)

fun Location.toDomain(): CarLocation = CarLocation(
    driverNumber = driverNumber,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    x = x,
    y = y,
    z = z,
    date = date.toInstantOrNull()
)

fun Meeting.toDomain(): GrandPrixMeeting = GrandPrixMeeting(
    meetingKey = meetingKey,
    meetingName = meetingName,
    meetingOfficialName = meetingOfficialName,
    circuitKey = circuitKey,
    circuitShortName = circuitShortName,
    circuitType = circuitType,
    countryCode = countryCode,
    countryKey = countryKey,
    countryName = countryName,
    gmtOffset = gmtOffset,
    location = location,
    year = year,
    isCancelled = isCancelled,
    dateStart = dateStart.toInstantOrNull(),
    dateEnd = dateEnd.toInstantOrNull()
)

fun Overtake.toDomain(): OvertakeEvent = OvertakeEvent(
    overtakingDriverNumber = overtakingDriverNumber,
    overtakenDriverNumber = overtakenDriverNumber,
    position = position,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    date = date.toInstantOrNull()
)

fun Pit.toDomain(): PitLaneEntry = PitLaneEntry(
    driverNumber = driverNumber,
    lapNumber = lapNumber,
    laneDuration = laneDuration,
    stopDuration = stopDuration,
    pitDuration = pitDuration,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    date = date.toInstantOrNull()
)

fun PitStopDto.toDomain(): PitStop = PitStop(
    driverNumber = driverNumber,
    lapNumber = lapNumber,
    stopNumber = stopNumber,
    pitDuration = pitDuration,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    date = date.toInstantOrNull()
)

fun Position.toDomain(): DriverPosition = DriverPosition(
    driverNumber = driverNumber,
    position = position,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    date = date.toInstantOrNull()
)

fun RaceControl.toDomain(): RaceControlMessage = RaceControlMessage(
    category = RaceControlCategory.from(category),
    flag = RaceControlFlag.from(flag),
    message = message,
    driverNumber = driverNumber,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    date = date
)

fun Session.toDomain(): RaceSession = RaceSession(
    sessionKey = sessionKey,
    sessionName = sessionName,
    sessionType = SessionType.from(sessionType ?: sessionName),
    meetingKey = meetingKey,
    circuitKey = circuitKey,
    circuitShortName = circuitShortName,
    countryCode = countryCode,
    countryKey = countryKey,
    countryName = countryName,
    location = location,
    year = year,
    gmtOffset = gmtOffset,
    dateStart = dateStart.toInstantOrNull(),
    dateEnd = dateEnd.toInstantOrNull()
)

fun Stint.toDomain(): TyreStint = TyreStint(
    driverNumber = driverNumber,
    stintNumber = stintNumber,
    compound = TyreCompound.from(compound),
    tyreAgeAtStart = tyreAgeAtStart,
    lapStart = lapStart,
    lapEnd = lapEnd,
    sessionKey = sessionKey,
    meetingKey = meetingKey
)

fun TeamRadio.toDomain(): TeamRadioMessage = TeamRadioMessage(
    driverNumber = driverNumber,
    recordingUrl = recordingUrl,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    date = date.toInstantOrNull()
)

fun Weather.toDomain(): TrackWeather = TrackWeather(
    airTemperature = airTemperature,
    trackTemperature = trackTemperature,
    humidity = humidity,
    pressure = pressure,
    windSpeed = windSpeed,
    windDirection = windDirection,
    isRaining = rainfall != null && rainfall > 0,
    sessionKey = sessionKey,
    meetingKey = meetingKey,
    date = date.toInstantOrNull()
)

package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Weather
import kotlinx.datetime.Instant

data class TrackWeather(
    val airTemperature: Double?,
    val trackTemperature: Double?,
    val humidity: Double?,
    val pressure: Double?,
    val windSpeed: Double?,
    val windDirection: Int?,
    val isRaining: Boolean?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: Instant?
) {
    companion object {
        fun from(dto: Weather): TrackWeather = TrackWeather(
            airTemperature = dto.airTemperature,
            trackTemperature = dto.trackTemperature,
            humidity = dto.humidity,
            pressure = dto.pressure,
            windSpeed = dto.windSpeed,
            windDirection = dto.windDirection,
            isRaining = dto.rainfall != null && dto.rainfall > 0,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            date = dto.date.toInstantOrNull()
        )
    }
}

package com.slashgil.openf1.domain.model

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
)

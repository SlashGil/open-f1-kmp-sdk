package com.slashgil.openf1.domain.model

data class DriverProfile(
    val driverNumber: Int?,
    val fullName: String?,
    val firstName: String?,
    val lastName: String?,
    val broadcastName: String?,
    val nameAcronym: String?,
    val countryCode: String?,
    val teamName: String?,
    val teamColour: String?,
    val headshotUrl: String?,
    val sessionKey: Int?,
    val meetingKey: Int?
)

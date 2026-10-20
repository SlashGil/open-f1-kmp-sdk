package com.slashgil.openf1.domain.model

import com.slashgil.openf1.model.Driver

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
) {
    companion object {
        fun from(dto: Driver): DriverProfile = DriverProfile(
            driverNumber = dto.driverNumber,
            fullName = dto.fullName,
            firstName = dto.firstName,
            lastName = dto.lastName,
            broadcastName = dto.broadcastName,
            nameAcronym = dto.nameAcronym,
            countryCode = dto.countryCode,
            teamName = dto.teamName,
            teamColour = dto.teamColour,
            headshotUrl = dto.headshotUrl,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey
        )
    }
}

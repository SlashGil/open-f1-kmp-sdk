package com.slashgil.openf1.domain.model

import com.slashgil.openf1.domain.model.enums.TyreCompound
import com.slashgil.openf1.model.Stint

data class TyreStint(
    val driverNumber: Int?,
    val stintNumber: Int?,
    val compound: TyreCompound,
    val tyreAgeAtStart: Int?,
    val lapStart: Int?,
    val lapEnd: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?
) {
    companion object {
        fun from(dto: Stint): TyreStint = TyreStint(
            driverNumber = dto.driverNumber,
            stintNumber = dto.stintNumber,
            compound = TyreCompound.from(dto.compound),
            tyreAgeAtStart = dto.tyreAgeAtStart,
            lapStart = dto.lapStart,
            lapEnd = dto.lapEnd,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey
        )
    }
}

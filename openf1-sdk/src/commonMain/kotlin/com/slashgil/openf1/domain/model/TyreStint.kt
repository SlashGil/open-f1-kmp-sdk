package com.slashgil.openf1.domain.model

import com.slashgil.openf1.domain.model.enums.TyreCompound

data class TyreStint(
    val driverNumber: Int?,
    val stintNumber: Int?,
    val compound: TyreCompound,
    val tyreAgeAtStart: Int?,
    val lapStart: Int?,
    val lapEnd: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?
)

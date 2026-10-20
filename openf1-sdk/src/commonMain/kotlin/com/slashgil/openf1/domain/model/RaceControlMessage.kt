package com.slashgil.openf1.domain.model

import com.slashgil.openf1.domain.model.enums.RaceControlCategory
import com.slashgil.openf1.domain.model.enums.RaceControlFlag

data class RaceControlMessage(
    val category: RaceControlCategory,
    val flag: RaceControlFlag,
    val message: String?,
    val driverNumber: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: String?
)

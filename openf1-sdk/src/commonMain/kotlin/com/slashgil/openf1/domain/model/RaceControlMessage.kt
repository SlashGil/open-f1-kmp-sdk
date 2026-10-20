package com.slashgil.openf1.domain.model

import com.slashgil.openf1.domain.model.enums.RaceControlCategory
import com.slashgil.openf1.domain.model.enums.RaceControlFlag
import com.slashgil.openf1.model.RaceControl

data class RaceControlMessage(
    val category: RaceControlCategory,
    val flag: RaceControlFlag,
    val message: String?,
    val driverNumber: Int?,
    val sessionKey: Int?,
    val meetingKey: Int?,
    val date: String?
) {
    companion object {
        fun from(dto: RaceControl): RaceControlMessage = RaceControlMessage(
            category = RaceControlCategory.from(dto.category),
            flag = RaceControlFlag.from(dto.flag),
            message = dto.message,
            driverNumber = dto.driverNumber,
            sessionKey = dto.sessionKey,
            meetingKey = dto.meetingKey,
            date = dto.date
        )
    }
}

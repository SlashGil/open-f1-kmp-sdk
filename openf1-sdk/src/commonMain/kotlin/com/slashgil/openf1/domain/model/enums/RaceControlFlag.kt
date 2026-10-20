package com.slashgil.openf1.domain.model.enums

enum class RaceControlFlag {
    GREEN,
    YELLOW,
    DOUBLE_YELLOW,
    RED,
    BLUE,
    CHEQUERED,
    BLACK_AND_WHITE,
    CLEAR,
    UNKNOWN;

    companion object {
        fun from(value: String?): RaceControlFlag = when (value?.uppercase()) {
            "GREEN" -> GREEN
            "YELLOW" -> YELLOW
            "DOUBLE YELLOW" -> DOUBLE_YELLOW
            "RED" -> RED
            "BLUE" -> BLUE
            "CHEQUERED" -> CHEQUERED
            "BLACK AND WHITE" -> BLACK_AND_WHITE
            "CLEAR" -> CLEAR
            null -> UNKNOWN
            else -> UNKNOWN
        }
    }
}

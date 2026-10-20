package com.slashgil.openf1.domain.model.enums

enum class TyreCompound {
    SOFT,
    MEDIUM,
    HARD,
    INTERMEDIATE,
    WET,
    TEST_UNKNOWN,
    UNKNOWN;

    companion object {
        fun from(value: String?): TyreCompound = when (value?.uppercase()) {
            "SOFT" -> SOFT
            "MEDIUM" -> MEDIUM
            "HARD" -> HARD
            "INTERMEDIATE" -> INTERMEDIATE
            "WET" -> WET
            "TEST_UNKNOWN" -> TEST_UNKNOWN
            null -> UNKNOWN
            else -> UNKNOWN
        }
    }
}

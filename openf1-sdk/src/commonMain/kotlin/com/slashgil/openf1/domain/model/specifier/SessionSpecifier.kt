package com.slashgil.openf1.domain.model.specifier

sealed interface SessionSpecifier {
    data class Key(val id: Int) : SessionSpecifier
    data object Latest : SessionSpecifier

    fun toParamString(): String = when (this) {
        is Key -> id.toString()
        is Latest -> "latest"
    }

    companion object {
        fun from(value: Any?): SessionSpecifier? = when (value) {
            is Int -> Key(value)
            is String -> if (value.equals("latest", ignoreCase = true)) Latest else value.toIntOrNull()?.let { Key(it) }
            else -> null
        }
    }
}

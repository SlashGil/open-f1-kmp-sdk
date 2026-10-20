package com.slashgil.openf1.domain.model

import kotlinx.datetime.Instant

internal fun String?.toInstantOrNull(): Instant? = if (this.isNullOrBlank()) null else try {
    Instant.parse(this)
} catch (_: Exception) {
    null
}

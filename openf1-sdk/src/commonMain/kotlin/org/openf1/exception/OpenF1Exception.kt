package org.openf1.exception

class OpenF1Exception(
    val statusCode: Int? = null,
    override val message: String,
    override val cause: Throwable? = null
) : Exception(message, cause)

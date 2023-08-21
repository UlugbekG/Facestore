package cd.ghost.common

import java.io.IOException

open class AppException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)

class ErrorResponseException(
    val code: Int,
    message: String? = null,
    throwable: Throwable?
) : AppException(cause = throwable, message = message)

class NoConnectionException(e: IOException) : AppException(cause = e)

class AuthException(message: String) : AppException(message = message)
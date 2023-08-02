package cd.ghost.common

import java.lang.Exception

open class AppException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)

class ParseBackendException(throwable: Throwable?) : AppException(cause = throwable)

class NoConnectionException : AppException()

class AuthException(message: String) : AppException(message = message)
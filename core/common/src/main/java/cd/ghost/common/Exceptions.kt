package cd.ghost.common

open class AppException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)

class ErrorResponseException(val code: Int, throwable: Throwable?) : AppException(cause = throwable)

class NoConnectionException : AppException()

class AuthException(message: String) : AppException(message = message)
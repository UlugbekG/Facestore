package cd.ghost.orders.domain.exceptions

import cd.ghost.common.AppException

enum class ExceptionType {
    FIRSTNAME, LASTNAME, ADDRESS
}

class EmptyException(val type: ExceptionType) : AppException()
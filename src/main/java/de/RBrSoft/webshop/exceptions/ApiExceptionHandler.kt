package de.RBrSoft.webshop.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler
    fun handleErrors( request: HttpServletRequest, exception: Exception): ResponseEntity<ErrorInfo> {

        println(exception.message)
        println(exception)

        val ( code, message ) = when (exception) {
            is IdNotFoundException -> exception.statusCode to exception.message
            is WebshopException -> exception.statusCode to exception.message
            else                -> HttpStatus.INTERNAL_SERVER_ERROR to (exception.message ?: "An error occurred")
        }

        val errorInfo = ErrorInfo(message, request.requestURI)
        return ResponseEntity(errorInfo, code)
    }


}

data class ErrorInfo(
    val error: String,
    val path: String
)
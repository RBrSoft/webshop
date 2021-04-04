package de.RBrSoft.webshop.exceptions

import org.springframework.http.HttpStatus

data class WebshopException(
    override val message: String,
    val statusCode: HttpStatus
): Exception(message)

data class IdNotFoundException(
    override val message: String,
    val statusCode: HttpStatus = HttpStatus.BAD_REQUEST
): Exception(message)

package de.RBrSoft.webshop.model

data class OrderCreatePositionRequest(
    val productId: String,
    val quantity: Long
)
package de.RBrSoft.webshop.repository

import de.RBrSoft.webshop.model.OrderCreateRequest
import de.RBrSoft.webshop.model.OrderResponse
import de.RBrSoft.webshop.model.OrderStatus
import java.time.LocalDateTime
import java.util.*

class OrderRepository {

    val orders = mutableListOf<OrderResponse>()

    fun save(request: OrderCreateRequest): OrderResponse {
        val orderResponse = OrderResponse(
            id = UUID.randomUUID().toString(),
            customerId = request.customerId,
            orderTime = LocalDateTime.now(),
            status = OrderStatus.NEW,
            orderPositions = emptyList()
        )
        orders.add(orderResponse)
        return orderResponse
    }

}

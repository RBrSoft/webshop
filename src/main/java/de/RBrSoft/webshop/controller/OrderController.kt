package de.RBrSoft.webshop.controller

import de.RBrSoft.webshop.model.OrderCreatePositionRequest
import de.RBrSoft.webshop.model.OrderCreateRequest
import de.RBrSoft.webshop.model.OrderResponse
import de.RBrSoft.webshop.model.OrderUpdateRequest
import de.RBrSoft.webshop.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(
    val orderService: OrderService
) {

    @PostMapping("/orders")
    fun createOrder(
        @RequestBody request: OrderCreateRequest
    ): OrderResponse {

        return orderService.createOrder(request)
     }

    @PostMapping("/orders/{id}/positions")
    fun createOrderPosition(
        @PathVariable(name = "id") orderId: String,
        @RequestBody request: OrderCreatePositionRequest
    ) {

        orderService.createNewPositionForOrder(orderId, request)

    }

    @PutMapping("/orders/{id}")
    fun updateOrder(
        @PathVariable id: String,
        @RequestBody request: OrderUpdateRequest
    ) {
        orderService.updateOrder(id, request)

    }


}
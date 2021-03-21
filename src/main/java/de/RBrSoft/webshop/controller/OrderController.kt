package de.RBrSoft.webshop.controller

import de.RBrSoft.webshop.model.OrderCreateRequest
import de.RBrSoft.webshop.model.OrderResponse
import de.RBrSoft.webshop.repository.OrderRepository
import de.RBrSoft.webshop.repository.ProductRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController {

    val orderRepository = OrderRepository()

    @PostMapping("/orders")
    fun createOrder(
        @RequestBody request: OrderCreateRequest
    ): OrderResponse {
        return orderRepository.save(request)
    }

}
package de.RBrSoft.webshop.service

import de.RBrSoft.webshop.model.*
import de.RBrSoft.webshop.repository.CustomerRepository
import de.RBrSoft.webshop.repository.OrderPositionRepository
import de.RBrSoft.webshop.repository.OrderRepository
import de.RBrSoft.webshop.repository.ProductRepository
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class OrderService(
    val productRepository: ProductRepository,
    val orderRepository: OrderRepository,
    val orderPositionRepository: OrderPositionRepository,
    val customerRepository: CustomerRepository
) {

    fun createOrder(request: OrderCreateRequest): OrderResponse {

        val customer: CustomerResponse = customerRepository.findById(request.customerId)
            ?: throw Exception("Customer not found!")

        return orderRepository.save(request)
    }

    fun createNewPositionForOrder(orderId: String, request: OrderCreatePositionRequest): OrderPositionResponse {

        orderRepository.findById(orderId)
            ?: throw Exception("Order not found!")

        if (productRepository.findById(request.productId).isEmpty)
            throw Exception("Product not found!")

        val orderPositionResponse = OrderPositionResponse(
            id = UUID.randomUUID().toString(),
            productId = request.productId,
            quantity = request.quantity
        )

        orderPositionRepository.save(orderPositionResponse)

        return orderPositionResponse
    }



}
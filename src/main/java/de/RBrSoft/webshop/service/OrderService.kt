package de.RBrSoft.webshop.service

import de.RBrSoft.webshop.exceptions.IdNotFoundException
import de.RBrSoft.webshop.exceptions.WebshopException
import de.RBrSoft.webshop.model.*
import de.RBrSoft.webshop.repository.CustomerRepository
import de.RBrSoft.webshop.repository.OrderPositionRepository
import de.RBrSoft.webshop.repository.OrderRepository
import de.RBrSoft.webshop.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
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
            ?: throw WebshopException(
                message = "Customer with Id: ${request.customerId} not found!",
                statusCode = HttpStatus.BAD_REQUEST
            )

        return orderRepository.save(request)
    }

    fun createNewPositionForOrder(orderId: String, request: OrderCreatePositionRequest): OrderPositionResponse {

        orderRepository.findById(orderId)
            ?: throw IdNotFoundException(
                message = "Order with Id: $orderId not found!",
                statusCode = HttpStatus.BAD_REQUEST
            )

        if (productRepository.findById(request.productId).isEmpty)
            throw WebshopException(
                message = "Product with Id: ${request.productId} not found!",
                statusCode = HttpStatus.BAD_REQUEST
            )

        val orderPositionResponse = OrderPositionResponse(
            id = UUID.randomUUID().toString(),
            productId = request.productId,
            quantity = request.quantity
        )

        orderPositionRepository.save(orderPositionResponse)

        return orderPositionResponse
    }



}
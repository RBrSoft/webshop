package de.RBrSoft.webshop.service

import de.RBrSoft.webshop.exceptions.IdNotFoundException
import de.RBrSoft.webshop.model.OrderPositionResponse
import de.RBrSoft.webshop.model.OrderResponse
import de.RBrSoft.webshop.model.ProductResponse
import de.RBrSoft.webshop.model.ShoppingCartResponse
import de.RBrSoft.webshop.repository.OrderPositionRepository
import de.RBrSoft.webshop.repository.OrderRepository
import de.RBrSoft.webshop.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ShoppingCartService(
    val orderRepository: OrderRepository,
    val orderPositionRepository: OrderPositionRepository,
    val productRepository: ProductRepository
) {


    fun getShoppingCartForCustomer(customerId: String): ShoppingCartResponse {

        val orders: List<OrderResponse> = orderRepository.findAllByCustomerIdWhereOrderStatusIsNew(customerId)
        val orderIds = orders.map { it.id }

        val orderPositions: List<OrderPositionResponse> = orderPositionRepository.findAllByOrderIds(orderIds)

        val deliveryCost: Long = 945
        val totalAmount = calculateSumForCart(orderPositions, deliveryCost)

        return ShoppingCartResponse(
            customerId = customerId,
            orderPositions = orderPositions,
            totalAmountInCent = totalAmount,
            deliveryCostInCent = deliveryCost,
            deliveryOption = "STANDARD"
        )

    }

    fun calculateSumForCart(
        orderPositions: List<OrderPositionResponse>,
        deliveryCost: Long
    ): Long {
        val positionAmounts: List<Long> = orderPositions.map {
            val product: ProductResponse = productRepository
                .findById(it.productId)
                .orElseThrow{ IdNotFoundException("Product with Id ${it.productId} not found") }
            if(it.quantity <= 0)
                throw IllegalArgumentException("OrderPosition with quantity of ${it.quantity} is not allowed.")
            it.quantity *  product.priceInCent
        }
        val positionSum = positionAmounts.sumBy { it.toInt() }
        return positionSum + deliveryCost
    }


}

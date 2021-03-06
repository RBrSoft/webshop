package de.RBrSoft.webshop.controller

import de.RBrSoft.webshop.model.CustomerResponse
import de.RBrSoft.webshop.model.ShoppingCartResponse
import de.RBrSoft.webshop.repository.CustomerRepository
import de.RBrSoft.webshop.service.ShoppingCartService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
    val customerRepository: CustomerRepository,
    val shoppingCartService: ShoppingCartService
) {

    @GetMapping("/customers/{id}")
    fun getCustomerById(
        @PathVariable id: String
    ): CustomerResponse {

        return customerRepository.findById(id)
    }

    @GetMapping("/customers/{id}/shoppingcart")
    fun getShoppingCartByCustomerId(
        @PathVariable id: String
    ): ShoppingCartResponse {

        return shoppingCartService.getShoppingCartForCustomer(id)
    }


}
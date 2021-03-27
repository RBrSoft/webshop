package de.RBrSoft.webshop.controller

import de.RBrSoft.webshop.model.CustomerResponse
import de.RBrSoft.webshop.repository.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
    val customerRepository: CustomerRepository
) {

    @GetMapping("/customers/{id}")
    fun getCustomerById(
            @PathVariable id: String): ResponseEntity<CustomerResponse> {

        val customer = customerRepository.findById(id)
        return if (customer != null)
            ResponseEntity.ok(customer)
        else
            ResponseEntity.notFound().build()
    }


}
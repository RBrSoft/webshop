package de.RBrSoft.webshop.repository

import de.RBrSoft.webshop.model.CustomerResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerRepository {

    val customers = listOf(
            CustomerResponse(
                    "1", // UUID.randomUUID().toString(),
                    "Roman",
                    "Breuer",
                    "roman.breuer@rbrSoft.de"
            ),
            CustomerResponse(
                    UUID.randomUUID().toString(),
                    "Anne",
                    "Grobleben",
                    "anne.grobleben@rbrSoft.de"
            ),
            CustomerResponse(
                    UUID.randomUUID().toString(),
                    "Clint",
                    "Eastwood",
                    "weiss@ich.net"
            )
    )

    fun findById(id: String): CustomerResponse? {
        return customers.find { it.id == id }
    }

}

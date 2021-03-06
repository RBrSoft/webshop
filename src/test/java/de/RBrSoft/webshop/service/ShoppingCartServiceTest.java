package de.RBrSoft.webshop.service;

import de.RBrSoft.webshop.exceptions.IdNotFoundException;
import de.RBrSoft.webshop.model.*;
import de.RBrSoft.webshop.repository.OrderPositionRepository;
import de.RBrSoft.webshop.repository.OrderRepository;
import de.RBrSoft.webshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ShoppingCartServiceTest {

    private ProductRepository productRepository;
    private ShoppingCartService service;

    @BeforeEach
    public void setupTests() {
        productRepository = mock(ProductRepository.class);
        service = new ShoppingCartService(
                mock(OrderRepository.class),
                mock(OrderPositionRepository.class),
                productRepository
        );
    }

    @Test
    public void testThat_calculateSumForEmptyCart_returnsDeliveryCost() {

        // given

        // when
        Long result = service.calculateSumForCart(new ArrayList<OrderPositionResponse>(), 500);

        // then
        assertThat(result).isEqualTo(500);
    }


    @Test
    public void testThat_calculateSumWithOneProduct_returnsSumsPriceOfProduct() {

        // given
        ProductResponse savedProduct = getSaveProduct(1000);

        given(productRepository.findById(savedProduct.getId())).willReturn(Optional.of(savedProduct));

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, savedProduct, 1);

        // when
        Long result = service.calculateSumForCart(orderPositions, 500);

        // then
        assertThat(result).isEqualTo(1500);
    }

    @Test
    public void testThat_calculateSumWithTwoProduct_returnsSumsPriceOfProducts() {

        // given
        ProductResponse savedProduct1 = getSaveProduct(1000);
        given(productRepository.findById(savedProduct1.getId())).willReturn(Optional.of(savedProduct1));

        ProductResponse savedProduct2 = getSaveProduct(2000);
        given(productRepository.findById(savedProduct2.getId())).willReturn(Optional.of(savedProduct2));

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, savedProduct1, 1);
        addOrderPosition(orderPositions, savedProduct2, 4);

        // when
        Long result = service.calculateSumForCart(orderPositions, 500);

        // then
        assertThat(result).isEqualTo(9500);
    }

    @Test
    public void testThat_calculateSumWithNotExistingProduct_throwsException() {

        // given
        ProductResponse notSavedProduct = new ProductResponse("", "", "", 1000, new ArrayList<>());

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, notSavedProduct, 1);

        // then
        assertThrows(IdNotFoundException.class, () -> {
            // when
                    service.calculateSumForCart(orderPositions, 500);
        });
    }

    @Test
    public void testThat_calculateSumWithNegativeQuantity_throwsException() {

        // given
        ProductResponse savedProduct1 = getSaveProduct(1000);
        given(productRepository.findById(savedProduct1.getId())).willReturn(Optional.of(savedProduct1));

        ProductResponse savedProduct2 = getSaveProduct(2000);
        given(productRepository.findById(savedProduct2.getId())).willReturn(Optional.of(savedProduct2));

        List<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, savedProduct1, 1);
        addOrderPosition(orderPositions, savedProduct2, -4);

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            service.calculateSumForCart(orderPositions, 500);
        });
    }


    private void addOrderPosition(List<OrderPositionResponse> orderPositions, ProductResponse saveProduct, long quantity) {

        orderPositions.add(
                new OrderPositionResponse("1",
                        "order-id",
                        saveProduct.getId(),
                        quantity)
        );
    }

    private ProductResponse getSaveProduct(int price) {
        return new ProductResponse(
                UUID.randomUUID().toString(),
                "",
                "",
                price,
                new ArrayList<>()
        );
    }

}

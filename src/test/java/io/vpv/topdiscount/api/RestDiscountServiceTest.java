package io.vpv.topdiscount.api;

import io.vpv.topdiscount.TopDiscountApplication;
import io.vpv.topdiscount.domain.*;
import io.vpv.topdiscount.exception.EntityNotFound;
import io.vpv.topdiscount.service.DiscountService;
import io.vpv.topdiscount.service.ItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestDiscountServiceTest extends TopDiscountApplication {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ItemService itemService;

    @Autowired
    DiscountService discountService;

    @BeforeEach
    void setUp() {
        itemService.save(new Item(123, "CLOTHES", 50.0));
        itemService.save(new Item(456, "ELECTRONICS", 300.0));
    }

    @AfterEach
    void tearDown() {
        itemService.deleteAll();
        discountService.deleteAllDiscounts();
    }

    @Test
    void createDiscount() {
        Discount discount = Discount.builder()
                .discountCode("ABC")
                .percentage(0.1)
                .discountType(DiscountType.ITEM_TYPE)
                .itemType("CLOTHES")
                .build();
        assertThat(getDiscountResponse(discount))
                .isNotNull();
    }


    @Test
    void deleteDiscountById() {

        Discount discount = Discount.builder()
                .discountCode("ABC")
                .percentage(0.1)
                .discountType(DiscountType.ITEM_TYPE)
                .itemType("CLOTHES")
                .build();
        Discount discountResponse = getDiscountResponse(discount);
        String endpoint = "http://localhost:" + port + "/api/discount/" + discountResponse.getId();
        this.restTemplate.delete(endpoint);

        assertThrows(RuntimeException.class,
                () -> restTemplate.getForObject(endpoint, ResponseEntity.class)
        );
    }

    private Discount getDiscountResponse(Discount discount) {
        String createEndpoint = "http://localhost:" + port + "/api/discount";
        return this.restTemplate.postForObject(createEndpoint, discount, Discount.class);
    }

    @Test
    void deleteAllDiscounts() {
        String endpoint = "http://localhost:" + port + "/api/discount";
        this.restTemplate.delete(endpoint + "/all");

        assertThrows(RuntimeException.class,
                () -> restTemplate.getForObject(endpoint, ResponseEntity.class)
        );
    }

    @Test
    void getBestDiscountUseCase1() {

        //Data Setup
        Discount discount1 = getDiscountResponse(Discount.builder()
                .discountCode("ABC")
                .percentage(0.1)
                .discountType(DiscountType.ITEM_TYPE)
                .itemType("CLOTHES")
                .build());
        Discount discount2 = getDiscountResponse(Discount.builder()
                .discountCode("CDE")
                .percentage(0.15)
                .discountType(DiscountType.ITEM_COST)
                .itemCost(100.0)
                .build());

        List<CartItems> cartItems = List.of(
                CartItems.builder().itemId(123).quantity(1).build()
        );
        System.out.println("Discount 1: " + discount1);
        System.out.println("Discount 2: " + discount2);
        System.out.println("Items: " + cartItems);

        String endpoint = "http://localhost:" + port + "/api/discount/best";
        assertThat(this.restTemplate.postForObject(endpoint, cartItems, CalculatedDiscount.class))
                .hasFieldOrPropertyWithValue("discountCode", "ABC")
                .hasFieldOrPropertyWithValue("totalCost", 45.0);
    }
    @Test
    void getBestDiscountUseCase2() {

        //Data Setup
        Discount discount1 = getDiscountResponse(Discount.builder()
                .discountCode("ABC")
                .percentage(0.1)
                .discountType(DiscountType.ITEM_TYPE)
                .itemType("CLOTHES")
                .build());
        Discount discount2 = getDiscountResponse(Discount.builder()
                .discountCode("CDE")
                .percentage(0.15)
                .discountType(DiscountType.ITEM_COST)
                .itemCost(100.0)
                .build());
    Discount discount3 = getDiscountResponse(Discount.builder()
            .discountCode("FGH")
            .percentage(0.2)
            .discountType(DiscountType.ITEM_COUNT)
            .itemCount(2)
            .build());

        List<CartItems> cartItems = List.of(
                CartItems.builder().itemId(123).quantity(5).build()
        );
        System.out.println("Discount 1: " + discount1);
        System.out.println("Discount 2: " + discount2);
        System.out.println("Discount 3: " + discount3);
        System.out.println("Items: " + cartItems);

        String endpoint = "http://localhost:" + port + "/api/discount/best";
        assertThat(this.restTemplate.postForObject(endpoint, cartItems, CalculatedDiscount.class))
                .hasFieldOrPropertyWithValue("discountCode", "FGH")
                .hasFieldOrPropertyWithValue("totalCost", 200.0);
    }

    @Test
    void getBestDiscountUseCase3() {

        //Data Setup
        Discount discount1 = getDiscountResponse(Discount.builder()
                .discountCode("ABC")
                .percentage(0.1)
                .discountType(DiscountType.ITEM_TYPE)
                .itemType("CLOTHES")
                .build());
        Discount discount2 = getDiscountResponse(Discount.builder()
                .discountCode("CDE")
                .percentage(0.15)
                .discountType(DiscountType.ITEM_COST)
                .itemCost(100.0)
                .build());

        List<CartItems> cartItems = List.of(
                CartItems.builder().itemId(123).quantity(1).build(),
                CartItems.builder().itemId(456).quantity(1).build()
        );;
        System.out.println("Discount 1: " + discount1);
        System.out.println("Discount 2: " + discount2);
        System.out.println("Items: " + cartItems);

        String endpoint = "http://localhost:" + port + "/api/discount/best";
        assertThat(this.restTemplate.postForObject(endpoint, cartItems, CalculatedDiscount.class))
                .hasFieldOrPropertyWithValue("discountCode", "CDE")
                .hasFieldOrPropertyWithValue("totalCost", 305.0);
    }

}
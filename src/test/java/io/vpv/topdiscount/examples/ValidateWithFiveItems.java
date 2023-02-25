package io.vpv.topdiscount.examples;

import io.vpv.topdiscount.TopDiscountApplication;
import io.vpv.topdiscount.domain.*;
import io.vpv.topdiscount.service.DiscountService;
import io.vpv.topdiscount.service.ItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class ValidateWithFiveItems extends TopDiscountApplication {
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
    }

    @Test
    void testDiscountAddition() {
        discountService.createDiscount(
                Discount.builder()
                        .discountCode("ABC")
                        .percentage(0.1)
                        .discountType(DiscountType.ITEM_TYPE)
                        .itemType("CLOTHES")
                .build());
        discountService.createDiscount(
                Discount.builder()
                        .discountCode("CDE")
                        .percentage(0.15)
                        .discountType(DiscountType.ITEM_COST)
                        .itemCost(100.0)
                        .build());
        discountService.createDiscount(
                Discount.builder()
                        .discountCode("FGH")
                        .percentage(0.2)
                        .discountType(DiscountType.ITEM_COUNT)
                        .itemCount(2)
                        .build());
        List<CartItems> cartItems = List.of(
                CartItems.builder().itemId(123).quantity(5).build()
        );
        CalculatedDiscount bestDiscount = discountService.getBestDiscount(cartItems);
        Assertions.assertTrue("FGH".equalsIgnoreCase(bestDiscount.getDiscountCode()));
        Assertions.assertTrue(200.0 == bestDiscount.getTotalCost());
    }
}

package io.vpv.topdiscount.service;

import io.vpv.topdiscount.TopDiscountApplication;
import io.vpv.topdiscount.domain.Discount;
import io.vpv.topdiscount.domain.DiscountType;
import io.vpv.topdiscount.domain.Item;
import io.vpv.topdiscount.exception.EntityNotFound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DiscountServiceTest  extends TopDiscountApplication {
    @Autowired
    DiscountService discountService;

    @BeforeEach
    void setUp() {
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
    }

    @AfterEach
    void tearDown() {
        discountService.deleteAllDiscounts();
    }

    @Test
    void testEntityNotFound() {
        Assertions.assertThrows( EntityNotFound.class, () -> discountService.deleteDiscount(23434));
    }

    @Test
    void testRunTimeExceptionForCreateDiscount() {
        Assertions.assertThrows( RuntimeException.class, () -> discountService.createDiscount(Discount.builder().id(100).build()));
    }
    @Test
    void testRunTimeExceptionForUpdateDiscount() {
        Assertions.assertThrows( RuntimeException.class, () -> discountService.updateDiscount(Discount.builder().build()));
    }
}

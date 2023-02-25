package io.vpv.topdiscount;

import io.vpv.topdiscount.api.RestDiscountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TopDiscountApplicationTests {

    @Autowired
    RestDiscountService restDiscountService;
    @Test
    void contextLoads() {
    }

    @Test
    void checkController() {
        assertThat(restDiscountService).isNotNull();
    }
}

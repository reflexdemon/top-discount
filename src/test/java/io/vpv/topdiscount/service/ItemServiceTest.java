package io.vpv.topdiscount.service;

import io.vpv.topdiscount.TopDiscountApplication;
import io.vpv.topdiscount.dao.ItemDao;
import io.vpv.topdiscount.domain.Item;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.management.openmbean.TabularData;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ItemServiceTest extends TopDiscountApplication {

    @Autowired
    ItemService itemService;

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
    void getAllItems() {
        List<Item> items = itemService.getAllItems();
        Assertions.assertFalse(items.isEmpty());
    }
}
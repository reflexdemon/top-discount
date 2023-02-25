package io.vpv.topdiscount.api;

import io.vpv.topdiscount.domain.CalculatedDiscount;
import io.vpv.topdiscount.domain.CartItems;
import io.vpv.topdiscount.domain.Discount;
import io.vpv.topdiscount.domain.Item;
import io.vpv.topdiscount.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestDiscountService {

    private DiscountService discountService;

    public RestDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping(value = "/api/discount")
    @ResponseStatus(HttpStatus.CREATED)
    public Discount createDiscount(@RequestBody Discount discount) {
        Discount result = discountService.createDiscount(discount);
        return result;
    }

    @DeleteMapping("/api/discount/{id}")
    public Discount deleteDiscountById(@PathVariable("id") Integer id) {
        return discountService.deleteDiscount(id);
    }


    @DeleteMapping("/api/discount/all")
    public void deleteAllDiscounts() {
        discountService.deleteAllDiscounts();
    }

    @PostMapping("/api/discount/best")
    public CalculatedDiscount getBestDiscount(@RequestBody List<CartItems> items) {
        return discountService.getBestDiscount(items);
    }
}

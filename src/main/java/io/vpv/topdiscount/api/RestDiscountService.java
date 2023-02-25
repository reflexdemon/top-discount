package io.vpv.topdiscount.api;

import io.vpv.topdiscount.domain.CalculatedDiscount;
import io.vpv.topdiscount.domain.CartItems;
import io.vpv.topdiscount.domain.Discount;
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

    @PostMapping(value = "/api/discount", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Discount createDiscount(@RequestBody Discount discount) {
        Discount result = discountService.createDiscount(discount);
        return result;
    }

    @DeleteMapping(value = "/api/discount/{id}", produces = "application/json")
    public Discount deleteDiscountById(@PathVariable("id") Integer id) {
        return discountService.deleteDiscount(id);
    }


    @DeleteMapping(value = "/api/discount/all", produces = "application/json")
    public void deleteAllDiscounts() {
        discountService.deleteAllDiscounts();
    }

    @PostMapping(value = "/api/discount/best", consumes = "application/json", produces = "application/json")
    public CalculatedDiscount getBestDiscount(@RequestBody List<CartItems> items) {
        return discountService.getBestDiscount(items);
    }

    @GetMapping(value = "/api/discount", produces = "application/json")
    public List<Discount> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }
}

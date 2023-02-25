package io.vpv.topdiscount.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ShoppingCart {
    private List<CartItems> items;
    private Double totalCost;
    private String discountCode;
}

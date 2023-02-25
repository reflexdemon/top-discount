package io.vpv.topdiscount.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CartItems {
    private Integer itemId;
    private Integer quantity;
    private Double cost;
}

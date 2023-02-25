package io.vpv.topdiscount.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CalculatedDiscount {
    private String discountCode;
    private Double totalCost;
}

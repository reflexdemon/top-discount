package io.vpv.topdiscount.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity(name = "discount")
@Builder
public class Discount {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String discountCode;
    private Double percentage;
    private DiscountType discountType;
    private String itemType; // ITEM_TYPE
    private Integer itemCount;// ITEM_COUNT
    private Double itemCost;// TOTAL_COST

}

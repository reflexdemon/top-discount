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
@Entity(name = "item")
public class Item {
    @Id
    private Integer id;
    private String itemType;
    private double cost;
}

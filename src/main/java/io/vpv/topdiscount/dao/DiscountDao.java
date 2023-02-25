package io.vpv.topdiscount.dao;

import io.vpv.topdiscount.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountDao extends JpaRepository<Discount, Integer> {
    public Discount findByDiscountCode(String discountCode);
}

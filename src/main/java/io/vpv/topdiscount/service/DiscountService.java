package io.vpv.topdiscount.service;

import io.vpv.topdiscount.dao.DiscountDao;
import io.vpv.topdiscount.domain.*;
import io.vpv.topdiscount.exception.EntityNotFound;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    private DiscountDao discountDao;

    private ItemService itemService;

    public DiscountService(DiscountDao discountDao, ItemService itemService) {
        this.discountDao = discountDao;
        this.itemService = itemService;
    }

    public List<Discount> getAllDiscounts() {
        return discountDao.findAll();
    }

    public Discount createDiscount(final Discount discount) {
        if (null != discount.getId() && discount.getId() != 0) {
            throw new RuntimeException("Cannot create Discount with ID already existing " + discount);
        }
        return discountDao.save(discount);
    }

    public Discount updateDiscount(final Discount discount) {
        if (null == discount || discount.getId() == 0) {
            throw new RuntimeException("Cannot update Discount without ID" + discount);
        }
        return discountDao.save(discount);
    }

    public Discount deleteDiscount(final Integer id) {
        Optional<Discount> discount = discountDao.findById(id);

        Discount result = discount.orElseThrow(
                () -> new EntityNotFound("Unable to find discount with id = " + id));
        discountDao.delete(result);

        return result;
    }

    public CalculatedDiscount getBestDiscount(List<CartItems> items) {
        List<Discount> discounts = getAllDiscounts();

        Optional<ShoppingCart> minCart = discounts.stream()
                .map(discount -> getTotalCost(discount, items))
                .sorted(Comparator.comparingDouble(ShoppingCart::getTotalCost))
                .findFirst();
        ShoppingCart bestOfferCart = minCart.orElseThrow();

        return CalculatedDiscount.builder()
                .totalCost(bestOfferCart.getTotalCost())
                .discountCode(bestOfferCart.getDiscountCode())
                .build();
    }

    private ShoppingCart getTotalCost(Discount discount, List<CartItems> items) {
        switch (discount.getDiscountType()) {
            case ITEM_TYPE:
                return computeBasedOnItemType(discount, items);
            case ITEM_COST:
                return computeBasedOnTotalCost(discount, items);
            case ITEM_COUNT:
                return computeBasedOnItemCount(discount, items);
        }
        return null;
    }

    private ShoppingCart computeBasedOnItemCount(Discount discount, List<CartItems> items) {
        ShoppingCart shoppingCart = getShoppingCart(items);
        shoppingCart.setDiscountCode(discount.getDiscountCode());
        //Apply Discount
        shoppingCart.setTotalCost(
                shoppingCart.getItems()
                        .stream()
                        .mapToDouble(item -> {
                            if (item.getQuantity() >= discount.getItemCount()) {
                                return getdiscountedPrice(item.getCost(), discount.getPercentage());
                            }
                            return item.getCost();
                        }).sum()
        );

        return shoppingCart;
    }

    private ShoppingCart computeBasedOnTotalCost(Discount discount, List<CartItems> items) {
        ShoppingCart shoppingCart = getShoppingCart(items);
        shoppingCart.setDiscountCode(discount.getDiscountCode());
        //Apply Discount
        shoppingCart.setTotalCost(
                shoppingCart.getItems()
                        .stream()
                        .mapToDouble(item -> {
                            if (item.getCost() >= discount.getItemCost()) {
                                return getdiscountedPrice(item.getCost(), discount.getPercentage());
                            }
                            return item.getCost();
                        }).sum()
        );

        return shoppingCart;
    }

    private ShoppingCart getShoppingCart(List<CartItems> items) {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .items(
                        items.stream().map(item -> {
                            //Calculate cost of each line item
                            item.setCost(itemService.getItemById(item.getItemId()).getCost() * item.getQuantity());
                            return item;
                        }).toList()
                ).build();
        //Full Price
        shoppingCart.setTotalCost(
                shoppingCart.getItems().stream()
                        .mapToDouble(CartItems::getCost)
                        .sum()
        );
        return shoppingCart;
    }

    private ShoppingCart computeBasedOnItemType(Discount discount, List<CartItems> items) {
        ShoppingCart shoppingCart = getShoppingCart(items);
        shoppingCart.setDiscountCode(discount.getDiscountCode());
        //Apply Discount
        shoppingCart.setTotalCost(
                shoppingCart.getItems()
                        .stream()
                        .mapToDouble(item -> {
                            Item dbItem = itemService.getItemById(item.getItemId());
                            if (dbItem.getItemType().equalsIgnoreCase(discount.getItemType())) {
                                return getdiscountedPrice(item.getCost(), discount.getPercentage());
                            }
                            return item.getCost();
                        }).sum()
        );

        return shoppingCart;
    }

    private double getdiscountedPrice(Double cost, Double percentage) {
        return cost - (cost * percentage);
    }

    public void deleteAllDiscounts() {
        discountDao.deleteAll();
    }
}

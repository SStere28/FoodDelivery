package com.epam.training.fooddelviery.domain;

import java.math.BigDecimal;

public class OrderItem {

    Food food;
    int amount;
    BigDecimal price;

    public OrderItem(Food food, int amount, BigDecimal price) {
        this.food = food;
        this.amount = amount;
        this.price = price;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "food=" + food +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}

package com.epam.training.fooddelviery.data;

import com.epam.training.fooddelviery.domain.Order;
import com.epam.training.fooddelviery.domain.OrderItem;

import java.util.List;

public interface DataStore {

    List<Order> getOrders();

}

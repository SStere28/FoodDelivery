package com.epam.training.fooddelviery.service;

import com.epam.training.fooddelviery.data.DataStore;
import com.epam.training.fooddelviery.domain.Food;
import com.epam.training.fooddelviery.domain.Order;
import com.epam.training.fooddelviery.domain.OrderItem;
import com.epam.training.fooddelviery.domain.Statistics;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class FoodDeliveryService {

    DataStore dataStore;

    public FoodDeliveryService(DataStore dataStore) {
        this.dataStore = dataStore;
    }
    public List<Order> getOrders(LocalDate startDate, LocalDate endDate){
        return dataStore.getOrders().stream().filter(ord -> ord.getOrderDate().compareTo(startDate.atStartOfDay())>=0 &&
                ord.getOrderDate().compareTo(endDate.atTime(LocalTime.from(LocalDate.now())))<=0 ).collect(Collectors.toList());
    }

    public Order getMostExpensiveOrder(){
        return dataStore.getOrders().stream().sorted(Comparator.comparing(Order::getTotalPrice).reversed()).collect(Collectors.toList()).get(0);
    }

    public Food getMostPopularFood(){
        Food food;
        List<OrderItem> orderItems=new ArrayList<>();
        Map<String,Long> foodAmount=new HashMap<>();
        foodAmount.put("Fideua", 0L);
        foodAmount.put("Paella", 0L);
        foodAmount.put("Tortilla", 0L);
        foodAmount.put("Gazpacho", 0L);
        foodAmount.put("Quesadilla", 0L);

       orderItems=dataStore.getOrders().stream().map(Order::getOrderItems)
              .flatMap(Collection::stream).collect(Collectors.toList());
       orderItems.stream().forEach(e-> foodAmount.put(e.getFood().getName(), (long) e.getAmount()+foodAmount.get(e.getFood().getName())));

       String foodName=foodAmount.entrySet().stream().max((entry1,entry2)->entry1.getValue()>
               entry2.getValue() ? 1:-1).get().getKey();
       food=new Food(foodName,BigDecimal.ONE );
       return food;
        }

    public long getMostLoyalCustomerId(){
        Map<Long,Long> count=dataStore.getOrders().stream().collect(Collectors.groupingBy(e->e.getCustomerId(), Collectors.counting()));
          return  count.keySet().stream().findFirst().get();
    }

    public Statistics getStatistics(LocalDate startDate, LocalDate endDate){
         Statistics statistics = new Statistics();
        List<Order> order=new ArrayList<>();
        order=getOrders(startDate,endDate);

        return  statistics;
//        order=dataStore.getOrders().stream().filter(ord -> ord.getOrderDate().compareTo(startDate.atStartOfDay())>=0 &&
//                ord.getOrderDate().compareTo(endDate.atTime(LocalTime.from(LocalDate.now())))<=0 ).collect(Collectors.toList());
//          BigDecimal totalIncome = new BigDecimal(0);
//        order.stream().forEach(e-> totalIncome+=e.getTotalPrice());
    }

}

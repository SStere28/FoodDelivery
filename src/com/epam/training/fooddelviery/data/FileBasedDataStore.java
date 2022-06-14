package com.epam.training.fooddelviery.data;

import com.epam.training.fooddelviery.domain.Food;
import com.epam.training.fooddelviery.domain.Order;
import com.epam.training.fooddelviery.domain.OrderItem;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FileBasedDataStore implements DataStore {

    private List<Order> orders;


    public FileBasedDataStore(String inputFilePath) {
        orders = new ArrayList<>();
        List<String> listClean = new ArrayList<>();

        try {
            File input = new File(inputFilePath);
            InputStream inputStream = new FileInputStream(input);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //  orders = bufferedReader.lines().map(mapToItem).collect(Collectors.toList());
            listClean = bufferedReader.lines().collect(Collectors.toList());
            bufferedReader.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        listClean.forEach(list -> {
            String[] orderValues = list.split(",");
            int orderID = Integer.parseInt(orderValues[0].replace("\uFEFF", ""));
            Optional<Order> orderByID = findOrderByID(orderID);

            Order order = orderByID.isPresent() ? orderByID.get() : new Order();

            int customerID = Integer.parseInt(orderValues[1]);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(orderValues[2], dateTimeFormatter);
            String foodName = orderValues[3];
            int foodPieces = Integer.parseInt(orderValues[4]);

            int foodPrice = getFoodPrice(foodName);
            Food food = new Food(foodName, BigDecimal.valueOf(foodPrice));
            OrderItem orderItem = new OrderItem(food, foodPieces, BigDecimal.valueOf(foodPrice));


            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.add(orderItem);

            if (orderByID.isEmpty()) {
                order.setId(orderID);
                order.setCustomerId(customerID);
                order.setOrderDate(localDateTime);
                order.setTotalPrice(BigDecimal.valueOf(foodPrice*foodPieces));
                orders.add(order);
            } else {
                order.setTotalPrice(BigDecimal.valueOf(foodPrice*foodPieces).add(order.getTotalPrice()));
            }

        });


    }


    private Optional<Order> findOrderByID(int orderID) {
        return orders.stream().filter(o -> o.getId() == orderID).findFirst();
    }

    public int getFoodPrice(String food) {

        switch (food) {
            case "Fideua":
                return 15;
            case "Paella":
                return 13;
            case "Tortilla":
                return 10;
            case "Gazpacho":
                return 8;
            case "Quesadilla":
                return 13;
            default:
                return -1;
        }

    }


    @Override
    public List<Order> getOrders() {
        return orders;
    }
}

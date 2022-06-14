package com.epam.training.fooddelviery;

import com.epam.training.fooddelviery.data.DataStore;
import com.epam.training.fooddelviery.data.FileBasedDataStore;
import com.epam.training.fooddelviery.domain.Food;
import com.epam.training.fooddelviery.domain.Order;
import com.epam.training.fooddelviery.service.FoodDeliveryService;
import com.epam.training.fooddelviery.view.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Application {

    public static void main(String[] args) {

        DataStore fileBasedDataStore =
                new FileBasedDataStore("C:\\Users\\Silviu\\Desktop\\JavaProjects\\FoodDelivery\\epamFood.csv");

        FoodDeliveryService foodDeliveryService=new FoodDeliveryService(fileBasedDataStore);
        View view=new View();
        view.printWelcomeMessage();
        view.printMostExpensiveOrder(foodDeliveryService.getMostExpensiveOrder());
        view.printMostPopularFood(foodDeliveryService.getMostPopularFood());
        view.printMostLoyalCustomer(foodDeliveryService.getMostLoyalCustomerId());

        LocalDate startDate=view.readStartDate();
        LocalDate endDate=view.readEndDate();
        view.printStatistics(foodDeliveryService.getStatistics(startDate,endDate),startDate,endDate);



    }

}

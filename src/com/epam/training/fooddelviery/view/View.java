package com.epam.training.fooddelviery.view;

import com.epam.training.fooddelviery.domain.Food;
import com.epam.training.fooddelviery.domain.Order;
import com.epam.training.fooddelviery.domain.Statistics;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class View {

    public View() {
    }
    public void printWelcomeMessage(){
        System.out.println("Welcome to Food Delivery Service \n");
    }
    public LocalDate readStartDate(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the start date (DD-MM-YYYY) :  ");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(keyboard.next(),dateTimeFormatter);
        return date;
    }
    public LocalDate readEndDate(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the end date (DD-MM-YYYY) :  ");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(keyboard.next(),dateTimeFormatter);
        return date;
    }
    public void printMostExpensiveOrder(Order order){
        System.out.println("The most expensive order was "+order.getId()+" with a total price of "+order.getTotalPrice()+" EUR");
    }
    public void printMostPopularFood(Food food){
        System.out.println("The most popular food is "+food.getName());
    }
    public void printMostLoyalCustomer(long customerId){
        System.out.println("The customer who ordered the most was: "+customerId);
    }
    public void printStatistics(Statistics statistics, LocalDate startDate, LocalDate endDate){
        if(statistics!=null){
        System.out.println("The statistics between "+startDate+" and "+endDate+":");
        System.out.println("The total income was: "+statistics.getTotalIncome()+" EUR");
        System.out.println("The average income per order: "+statistics.getAverageIncomeByOrder()+" EUr");
        System.out.println("There were "+statistics.getNumberOfFood()+" dishes served");
        System.out.println("There were "+statistics.getNumberOfOrder()+" orders made");
        System.out.println("The day with the highest income: "+statistics.getDayOfHighestIncome());
        } else System.out.println("No orders were found between "+startDate+" and "+endDate);

    }
}

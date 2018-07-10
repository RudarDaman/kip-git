package com.knoldus;

import java.util.Scanner;

public class ConverterApplication {

    public static void main(String[] args){

        System.out.println("List of Currency Codes");
        System.out.println("1. Indian Rupee: INR");
        System.out.println("2. United States Dollar: USD");
        System.out.println("3. EURO: EUR");
        System.out.println("4. British Pound: GBP");
        System.out.println("5. American Dollar: AUD");

        //Input Two Currencies
        Scanner sr = new Scanner(System.in);

        //First Currency Input
        System.out.println("Enter the Currency From You want Conversion:");
        String firstCurrency = sr.next().toUpperCase();
        //System.out.println(firstCurrency);
        System.out.println("Enter the Amount:");
        Double firstValue = sr.nextDouble();
        //System.out.println(firstValue);

        //Second Currency Input
        System.out.println("Enter the Currency To Which You want Conversion:");
        String secondCurrency = sr.next().toUpperCase();
        //System.out.println(secondCurrency);


        //Conversion
        Double secondValue = Converter.calculate(firstCurrency, firstValue, secondCurrency);
        System.out.println(firstValue + " " + firstCurrency + " = " + secondValue + " " + secondCurrency);

    }

}

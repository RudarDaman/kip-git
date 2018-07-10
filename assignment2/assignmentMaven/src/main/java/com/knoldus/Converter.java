package com.knoldus;

import java.util.*;

public class Converter {
    public static Double calculate(String firstCurrency, Double firstValue, String secondCurrency) {

        Double secondValue;

        Map<String, Double> chart = new HashMap<>();
        chart.put("INR", 1.00);
        chart.put("USD", 68.88);
        chart.put("EUR", 80.90);
        chart.put("GBP", 91.33);
        chart.put("AUD", 51.17);

        secondValue = Math.round((chart.get(firstCurrency) * firstValue * 100) / chart.get(secondCurrency)) / 100.0;

        return secondValue;
    }
}

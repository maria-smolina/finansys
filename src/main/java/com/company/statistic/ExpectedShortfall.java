package com.company.statistic;

import java.util.List;

public class ExpectedShortfall {
    public static double expectedShortfall(List<Double> yields, long days, double percent) {
        if (yields.size() < days) {
            throw new IllegalArgumentException("Too few elements in list");
        }
        return yields.stream()
                .skip(yields.size() - days)
                .sorted()
                .limit((long) Math.floor(days * 0.05))
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}

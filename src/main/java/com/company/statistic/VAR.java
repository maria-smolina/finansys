package com.company.statistic;

import java.util.List;

public class VAR {
    private static double STANDARD_DEVIATION_FOR_95_PERCENT = -1.644853627;
    private static double STANDARD_DEVIATION_FOR_99_PERCENT = -2.326347874;

    public static double historicalVAR(List<Double> yields, long days, double percent) {
        if (yields.size() < days) {
            throw new IllegalArgumentException("Too few elements in list");
        }
        double var = yields.stream()
                .skip(yields.size() - days)
                .sorted()
                .skip((long) Math.floor(days * percent))
                .findFirst().orElse(0.0);
        return Math.abs(var);
    }

    public static double parametric95VAR(List<Double> yields, long days) {
        double var = Statistic.standardDeviation(yields) * Math.pow(days, 0.5) * STANDARD_DEVIATION_FOR_95_PERCENT;
        return Math.abs(var);
    }

    public static double parametric99VAR(List<Double> yields, long days) {
        double var = Statistic.standardDeviation(yields) * Math.pow(days, 0.5) * STANDARD_DEVIATION_FOR_99_PERCENT;
        return Math.abs(var);
    }
}

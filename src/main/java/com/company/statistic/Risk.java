package com.company.statistic;

import java.util.List;

import static com.company.statistic.Yield.averageYield;

public class Risk {
    public static double standardDeviation(List<Double> yields) {
        return Math.pow(dispersion(yields), 0.5);
    }

    public static double dispersion(List<Double> yields) {
        double averageYield = averageYield(yields);
        return yields.stream()
                .mapToDouble(y -> Math.pow(y - averageYield, 2.0))
                .average().orElse(0.0);
    }

    public static double covariance(List<Double> yields1, List<Double> yields2) {
        if (yields1.size() != yields2.size()) {
            throw new IllegalArgumentException("Sizes of lists are not equal");
        }
        double averageYield1 = averageYield(yields1);
        double averageYield2 = averageYield(yields2);
        int size = yields1.size();
        double cov = 0;
        for (int i = 0; i < size; i++) {
            cov += (yields1.get(i) - averageYield1) * (yields2.get(i) - averageYield2);
        }
        return cov / (size - 1);
    }

    public static double correlation(List<Double> yields1, List<Double> yields2) {
        return covariance(yields1, yields2) / (standardDeviation(yields1) * standardDeviation(yields2));
    }
}

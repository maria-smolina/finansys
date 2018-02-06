package com.company.statistic;

import java.util.List;

public class HedgeRatio {
    public static double hedgeRatio(List<Double> spot, List<Double> forward) {
        return Statistic.covariance(spot, forward) / Statistic.standardDeviation(forward);
    }
}

package com.company.statistic;

public class FRA {
    public static double fra(double commonRate, double commonTime, double firstPartRate, double firstPartTime) {
        if (commonTime < firstPartTime) {
            throw new IllegalArgumentException("First part time should be less than common time");
        }
        // because of no-arbitrage principle
        return Math.pow(
                Math.pow(1 + commonRate, commonTime) / Math.pow(1 + firstPartRate, firstPartTime),
                1 / (commonTime - firstPartTime))
                - 1;
    }
}

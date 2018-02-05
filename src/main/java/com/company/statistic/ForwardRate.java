package com.company.statistic;

public class ForwardRate {
    public static double forwardRateRUBUSD(double rate, double mosprime, double libor, double timeInYear) {
        // e ^ ((mosprime - libor) * time) is the second remarkable limit
        return rate * Math.pow(Math.E, (mosprime - libor) * timeInYear);
    }
}

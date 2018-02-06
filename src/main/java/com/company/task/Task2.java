package com.company.task;

import com.company.model.Security;
import com.company.parser.csv.Parser;
import com.company.parser.csv.Stock;
import com.company.statistic.FRA;
import com.company.statistic.ForwardRate;
import com.company.statistic.HedgeRatio;
import com.company.statistic.Yield;

import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    private static final double MONTH = 1./12;
    private static final double TWO_MONTHS = 2./12;
    private static final double THREE_MONTHS = 3./12;
    private static final double SIX_MONTHS = 6./12;

    public static void main(String[] args) {
        double rate = 34.8789;
        System.out.println("Rate for 14.05.2014: " + rate);

        // rates from http://mosprime.com/period.html?d1=01.03.2014
        double mosprime1m = 0.0927;
        double mosprime2m = 0.0954;
        double mosprime3m = 0.0982;
        double mosprime6m = 0.0995;

        double libor1m = 0.001511;
        double libor2m = 0.0019;
        double libor3m = 0.0022535;
        double libor6m = 0.0032390;

        // forward rate RUB/USD for 1 month
        double forwardRateRUBUSD1month = ForwardRate.forwardRateRUBUSD(rate, mosprime1m, libor1m, MONTH);
        System.out.println("\nForward rate RUB/USD for 1 month: " + forwardRateRUBUSD1month);
        double spotRate1month = 34.5654000;
        System.out.println("Spot rate RUB/USD for 1 month: " + spotRate1month);

        // forward rate RUB/USD for 2 months
        double forwardRateRUBUSD2months = ForwardRate.forwardRateRUBUSD(rate, mosprime2m, libor2m, TWO_MONTHS);
        System.out.println("\nForward rate RUB/USD for 2 months: " + forwardRateRUBUSD2months);
        double spotRate2months = 34.3135000;
        System.out.println("Spot rate RUB/USD for 2 months: " + spotRate2months);

        // forward rate RUB/USD for 3 months
        double forwardRateRUBUSD3months = ForwardRate.forwardRateRUBUSD(rate, mosprime3m, libor3m, THREE_MONTHS);
        System.out.println("\nForward rate RUB/USD for 3 months: " + forwardRateRUBUSD3months);
        double spotRate3months = 36.2222000;
        System.out.println("Spot rate RUB/USD for 3 months: " + spotRate3months);

        // forward rate RUB/USD for 6 months
        double forwardRateRUBUSD6months = ForwardRate.forwardRateRUBUSD(rate, mosprime6m, libor6m, SIX_MONTHS);
        System.out.println("\nForward rate RUB/USD for 6 months: " + forwardRateRUBUSD6months);
        double spotRate6months = 46.1233000;
        System.out.println("Spot rate RUB/USD for 6 months: " + spotRate6months);

        // FRA rate
        double fra1x2 = FRA.fra(mosprime2m, TWO_MONTHS, mosprime1m, MONTH);
        System.out.println("\nFRA 1x2: " + fra1x2);
        double spotRate1x2 = 0.0921;
        System.out.println("Spot rate: " + spotRate1x2);

        double fra1x3 = FRA.fra(mosprime3m, THREE_MONTHS, mosprime1m, MONTH);
        System.out.println("\nFRA 1x3: " + fra1x3);
        double spotRate1x3 = 0.0936;
        System.out.println("Spot rate: " + spotRate1x3);

        double fra1x6 = FRA.fra(mosprime6m, SIX_MONTHS, mosprime1m, MONTH);
        System.out.println("\nFRA 1x6: " + fra1x6);
        System.out.println("Spot rate: -");

        double fra2x3 = FRA.fra(mosprime3m, THREE_MONTHS, mosprime2m, TWO_MONTHS);
        System.out.println("\nFRA 2x3: " + fra2x3);
        double spotRate2x3 = 0.09;
        System.out.println("Spot rate: " + spotRate2x3);

        double fra2x6 = FRA.fra(mosprime6m, SIX_MONTHS, mosprime2m, TWO_MONTHS);
        System.out.println("\nFRA 2x6: " + fra2x6);
        System.out.println("Spot rate: -");

        double fra3x6 = FRA.fra(mosprime6m, SIX_MONTHS, mosprime3m, THREE_MONTHS);
        System.out.println("\nFRA 3x6: " + fra3x6);
        double spotRate3x6 = 0.1010;
        System.out.println("Spot rate: " + spotRate3x6);

        // Hedge ratio
        String file = "build/resources/main/SBER_090119_180119.csv";
        Parser parser = new Parser();
        List<Stock> stocks = parser.csvToStock(file);
        List<Security> securities = stocks.stream().map(com.company.model.Stock::new).collect(Collectors.toList());
        List<Double> spot = Yield.yields(securities);
        file = "build/resources/main/RI.MICEX10INDEX_090119_180119.csv";
        stocks = parser.csvToStock(file);
        securities = stocks.stream().map(com.company.model.Stock::new).collect(Collectors.toList());
        List<Double> forward = Yield.yields(securities);

        double hedgeRatio = HedgeRatio.hedgeRatio(spot, forward);
        System.out.println("Hedge ratio: " + hedgeRatio);

        hedgeRatio = HedgeRatio.hedgeRatio(forward, spot);
        System.out.println("Hedge ratio conversely: " + hedgeRatio);

    }
}

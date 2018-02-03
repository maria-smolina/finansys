package com.company.task;

import com.company.model.Security;
import com.company.parser.csv.Parser;
import com.company.parser.csv.Stock;
import com.company.statistic.ExpectedShortfall;
import com.company.statistic.Risk;
import com.company.statistic.VAR;
import com.company.statistic.Yield;

import java.util.List;
import java.util.stream.Collectors;

public class Task1 {
    public static void main(String[] args) {
        String file = "build/resources/main/SBER_090119_180119.csv";
        Parser parser = new Parser();
        List<Stock> stocks = parser.csvToStock(file);
        List<Security> securities = stocks.stream().map(com.company.model.Stock::new).collect(Collectors.toList());

        // yields
        System.out.println("Yields:");
        List<Double> yields = Yield.yields(securities);
        for (Double yield : yields) {
            System.out.println(String.format("%.5f", yield));
        }

        // average yield, volatility
        double averageYield = Yield.averageYield(yields);
        System.out.println("\nAverage yield: " + averageYield);
        double volatility = Risk.standardDeviation(yields);
        System.out.println("\nVolatility: " + volatility);

        // historical var
        double historicalVAR99percent = VAR.historicalVAR(yields, 251, 0.01);
        System.out.println("\nHistorical VAR for 251 days, 1%: " + historicalVAR99percent);
        double historicalVAR95percent = VAR.historicalVAR(yields, 1255, 0.05);
        System.out.println("\nHistorical VAR for 1255 days, 5%: " + historicalVAR95percent);

        // 1 day parametric var
        double parametric1dayVAR99percent = VAR.parametric99VAR(yields, 1);
        System.out.println("\nParametric 1 day VAR, 1%: " + parametric1dayVAR99percent);
        double parametric1dayVAR95percent = VAR.parametric95VAR(yields, 1);
        System.out.println("\nParametric 1 day VAR, 5%: " + parametric1dayVAR95percent);

        // 10 days parametric var
        double parametric10dayVAR99percent = VAR.parametric99VAR(yields, 10);
        System.out.println("\nParametric 10 days VAR, 1%: " + parametric10dayVAR99percent);
        double parametric10dayVAR95percent = VAR.parametric95VAR(yields, 10);
        System.out.println("\nParametric 10 days VAR, 5%: " + parametric10dayVAR95percent);

        // expected shortfall
        double expectedShortfall = ExpectedShortfall.expectedShortfall(yields, 1255, 0.05);
        System.out.println("\nExpected shortfall, 5%: " + expectedShortfall);

        // correlation
        file = "build/resources/main/RI.MICEX10INDEX_090119_180119.csv";
        stocks = parser.csvToStock(file);
        securities = stocks.stream().map(com.company.model.Stock::new).collect(Collectors.toList());
        List<Double> yields2 = Yield.yields(securities);
        System.out.println("\nCorrelation: " + Risk.correlation(yields, yields2));
    }
}

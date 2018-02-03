package com.company.task;

import com.company.model.Security;
import com.company.parser.csv.Parser;
import com.company.parser.csv.Stock;
import com.company.statistic.Yield;

import java.util.List;
import java.util.stream.Collectors;

public class VAR {
    public static void main(String[] args) {
        String file = "build/resources/main/MOEX_170119_180119.csv";
        Parser parser = new Parser();
        List<Stock> stocks = parser.csvToStock(file);
        List<Security> securities = stocks.stream().map(com.company.model.Stock::new).collect(Collectors.toList());

        // yields
        List<Double> yields = Yield.yields(securities);
        for (Double yield : yields) {
            System.out.println(String.format("%.5f", yield));
        }
    }
}

package com.company;

import com.company.model.Security;
import com.company.parser.csv.HalfYearRate;
import com.company.parser.csv.Parser;
import com.company.parser.csv.Stock;
import com.company.statistic.Yield;
import com.company.task.Task1;
import com.company.task.Task2;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class FinansysApplication {
    public static final DecimalFormat DF = new DecimalFormat("0.000000");

    public static void main(String[] args) throws IOException {
        String file = "quotes1.csv";
        Parser parser = new Parser();
        List<Stock> stocks = parser.csvToStock(file);
        List<Security> securities = stocks.stream().map(com.company.model.Stock::new).collect(Collectors.toList());
        List<Double> yields = Yield.yields(securities);

        file = "quotes2.csv";
        stocks = parser.csvToStock(file);
        securities = stocks.stream().map(com.company.model.Stock::new).collect(Collectors.toList());
        List<Double> yields2 = Yield.yields(securities);

        file = "mosprime.csv";
        HalfYearRate mosprime = parser.csvToHalfYearRate(file);

        file = "libor.csv";
        HalfYearRate libor = parser.csvToHalfYearRate(file);

        File task1File = new File("task1.txt");
        task1File.createNewFile();
        File task1YieldsFile = new File("task1_yields.txt");
        task1File.createNewFile();
        File task2File = new File("task2.txt");
        task2File.createNewFile();

        Task1.run(yields, yields2, new PrintStream(task1YieldsFile), new PrintStream(task1File));
		Task2.run(yields, yields2, new PrintStream(task2File), mosprime, libor);
	}
}

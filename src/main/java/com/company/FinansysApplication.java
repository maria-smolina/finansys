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

        File fileRow1 = new File("task1_row1.txt");
        fileRow1.createNewFile();
        File fileYields1 = new File("task1_yields1.txt");
        fileYields1.createNewFile();

        Task1.run(yields, new PrintStream(fileYields1), new PrintStream(fileRow1));

        File fileRow2 = new File("task1_row2.txt");
        fileRow2.createNewFile();
        File fileYelds2 = new File("task1_yields2.txt");
        fileYelds2.createNewFile();

        Task1.run(yields2, new PrintStream(fileYelds2), new PrintStream(fileRow2));

        File correlationFile = new File("task1_correlation.txt");
        correlationFile.createNewFile();
        Task1.correlation(yields, yields2, new PrintStream(correlationFile));

        File fileTask2 = new File("task2.txt");
        fileTask2.createNewFile();

		Task2.run(yields, yields2, new PrintStream(fileTask2), mosprime, libor);
	}
}

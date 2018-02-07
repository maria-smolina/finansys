package com.company.parser.csv;

import com.google.common.io.ByteStreams;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.List;

@Slf4j
public class Parser {
    public List<Stock> csvToStock(String file) {
        log.info("Parsing csv file " + file);
        // TODO delete this redirection
        PrintStream st = new PrintStream(ByteStreams.nullOutputStream());
        PrintStream standardStream = System.out;
        System.setOut(st);
        List<Stock> stocks = null;
        try {
            stocks = new CsvToBeanBuilder<Stock>(new FileReader(file))
                    .withType(Stock.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            System.setOut(standardStream);
            log.error("csvToStock error", e);
        }
        System.setOut(standardStream);
        log.info("Parsing finished");
        return stocks;
    }

    public HalfYearRate csvToHalfYearRate(String file) {
        log.info("Parsing csv file " + file);
        // TODO delete this redirection
        PrintStream st = new PrintStream(ByteStreams.nullOutputStream());
        PrintStream standardStream = System.out;
        System.setOut(st);
        List<HalfYearRate> rates = null;
        try {
            rates = new CsvToBeanBuilder<HalfYearRate>(new FileReader(file))
                    .withType(HalfYearRate.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            System.setOut(standardStream);
            log.error("csvToHalfYearRate error", e);
        }
        System.setOut(standardStream);
        log.info("Parsing finished");
        return rates != null && !rates.isEmpty() ? rates.get(0) : null;
    }
}

package com.company.parser.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Slf4j
public class Parser {
    public List<Stock> csvToStock(String file) {
        try {
            return new CsvToBeanBuilder<Stock>(new FileReader(file))
                    .withType(Stock.class)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            log.error("csvToStock error", e);
        }
        return null;
    }
}

package com.company.parser.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.util.Date;

@Data
public class Stock {
    @CsvBindByName(column = "<TICKER>")
    private String ticker;

    @CsvBindByName(column = "<PER>")
    private long per;

    @CsvBindByName(column = "<DATE>")
    @CsvDate("yyyyMMdd")
    private Date date;

    @CsvBindByName(column = "<TIME>")
    @CsvDate("HHmmss")
    private Date time;

    @CsvBindByName(column = "<OPEN>")
    private double open;

    @CsvBindByName(column = "<HIGH>")
    private double high;

    @CsvBindByName(column = "<LOW>")
    private double low;

    @CsvBindByName(column = "<CLOSE>")
    private double close;

    @CsvBindByName(column = "<VOL>")
    private double volume;
}

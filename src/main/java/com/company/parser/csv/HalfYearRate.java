package com.company.parser.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class HalfYearRate {
    @CsvBindByName(column = "<1m>")
    private double month1;

    @CsvBindByName(column = "<2m>")
    private double month2;

    @CsvBindByName(column = "<3m>")
    private double month3;

    @CsvBindByName(column = "<6m>")
    private double month6;
}

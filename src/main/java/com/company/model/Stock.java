package com.company.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Data
public class Stock implements Security {
    private String ticker;

    private String per;

    private LocalDateTime time;

    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;

    public Stock(com.company.parser.csv.Stock stock) {
        ticker = stock.getTicker();
        per = stock.getPer();
        time = getLocalDateTime(stock.getDate(), stock.getTime());
        open = stock.getOpen();
        high = stock.getHigh();
        low = stock.getLow();
        close = stock.getClose();
        volume = stock.getVolume();
    }

    @Override
    public double getPrice() {
        return close;
    }

    public static LocalDateTime getLocalDateTime(Date date, Date time) {
        if (date == null || time == null) {
            return null;
        }
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime localTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).toLocalTime();
        return LocalDateTime.of(localDate, localTime);
    }
}

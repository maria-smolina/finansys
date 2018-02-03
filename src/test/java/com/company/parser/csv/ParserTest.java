package com.company.parser.csv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static com.company.model.Stock.getLocalDateTime;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserTest {
    @Test
    public void csvToStockTest() {
        String file = "build/resources/test/MOEX_170119_170119.csv";
        Parser parser = new Parser();
        List<Stock> stocks = parser.csvToStock(file);
        assertEquals(1, stocks.size());
        Stock stock = stocks.get(0);
        assertEquals("MOEX", stock.getTicker());
        assertEquals("60", stock.getPer());
        assertEquals(LocalDateTime.of(2017, 1, 19, 11, 0),
                getLocalDateTime(stock.getDate(), stock.getTime()));
        assertEquals(131.77, stock.getOpen(), 0);
        assertEquals(133.29, stock.getHigh(), 0);
        assertEquals(131.55, stock.getLow(), 0);
        assertEquals(133.23, stock.getClose(), 0);
        assertEquals(799140, stock.getVolume(), 0);
    }
}

package com.company.task;

import com.company.parser.csv.HalfYearRate;
import com.company.statistic.FRA;
import com.company.statistic.ForwardRate;
import com.company.statistic.HedgeRatio;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;
import java.util.List;

import static com.company.FinansysApplication.DF;

@Slf4j
public class Task2 {
    private static final double MONTH = 1./12;
    private static final double TWO_MONTHS = 2./12;
    private static final double THREE_MONTHS = 3./12;
    private static final double SIX_MONTHS = 6./12;

    public static void run(List<Double> yields, List<Double> yields2, PrintStream out,
                           HalfYearRate mosprime, HalfYearRate libor) {
        // rates from http://mosprime.com/period.html?d1=01.03.2014
        log.info("Task2 is executing");

        double rate = 34.8789;
        out.println("Rate for 14.05.2014: " + DF.format(rate));

        // forward rate RUB/USD for 1 month
        double forwardRateRUBUSD1month =
                ForwardRate.forwardRateRUBUSD(rate, mosprime.getMonth1(), libor.getMonth1(), MONTH);
        out.println("\nForward rate RUB/USD for 1 month: " + DF.format(forwardRateRUBUSD1month));
        double spotRate1month = 34.5654000;
        out.println("Spot rate RUB/USD for 1 month: " + DF.format(spotRate1month));

        // forward rate RUB/USD for 2 months
        double forwardRateRUBUSD2months =
                ForwardRate.forwardRateRUBUSD(rate, mosprime.getMonth2(), libor.getMonth2(), TWO_MONTHS);
        out.println("\nForward rate RUB/USD for 2 months: " + DF.format(forwardRateRUBUSD2months));
        double spotRate2months = 34.3135000;
        out.println("Spot rate RUB/USD for 2 months: " + DF.format(spotRate2months));

        // forward rate RUB/USD for 3 months
        double forwardRateRUBUSD3months =
                ForwardRate.forwardRateRUBUSD(rate, mosprime.getMonth3(), libor.getMonth3(), THREE_MONTHS);
        out.println("\nForward rate RUB/USD for 3 months: " + DF.format(forwardRateRUBUSD3months));
        double spotRate3months = 36.2222000;
        out.println("Spot rate RUB/USD for 3 months: " + DF.format(spotRate3months));

        // forward rate RUB/USD for 6 months
        double forwardRateRUBUSD6months =
                ForwardRate.forwardRateRUBUSD(rate, mosprime.getMonth6(), libor.getMonth6(), SIX_MONTHS);
        out.println("\nForward rate RUB/USD for 6 months: " + DF.format(forwardRateRUBUSD6months));
        double spotRate6months = 46.1233000;
        out.println("Spot rate RUB/USD for 6 months: " + DF.format(spotRate6months));

        // FRA rate
        double fra1x2 = FRA.fra(mosprime.getMonth2(), TWO_MONTHS, mosprime.getMonth1(), MONTH);
        out.println("\nFRA 1x2: " + DF.format(fra1x2));
        double spotRate1x2 = 0.0921;
        out.println("Spot rate: " + DF.format(spotRate1x2));

        double fra1x3 = FRA.fra(mosprime.getMonth3(), THREE_MONTHS, mosprime.getMonth1(), MONTH);
        out.println("\nFRA 1x3: " + DF.format(fra1x3));
        double spotRate1x3 = 0.0936;
        out.println("Spot rate: " + DF.format(spotRate1x3));

        double fra1x6 = FRA.fra(mosprime.getMonth6(), SIX_MONTHS, mosprime.getMonth1(), MONTH);
        out.println("\nFRA 1x6: " + DF.format(fra1x6));
        out.println("Spot rate: -");

        double fra2x3 = FRA.fra(mosprime.getMonth3(), THREE_MONTHS, mosprime.getMonth2(), TWO_MONTHS);
        out.println("\nFRA 2x3: " + DF.format(fra2x3));
        double spotRate2x3 = 0.09;
        out.println("Spot rate: " + DF.format(spotRate2x3));

        double fra2x6 = FRA.fra(mosprime.getMonth6(), SIX_MONTHS, mosprime.getMonth2(), TWO_MONTHS);
        out.println("\nFRA 2x6: " + DF.format(fra2x6));
        out.println("Spot rate: -");

        double fra3x6 = FRA.fra(mosprime.getMonth6(), SIX_MONTHS, mosprime.getMonth3(), THREE_MONTHS);
        out.println("\nFRA 3x6: " + DF.format(fra3x6));
        double spotRate3x6 = 0.1010;
        out.println("Spot rate: " + DF.format(spotRate3x6));

        // Hedge ratio
        double hedgeRatio = HedgeRatio.hedgeRatio(yields, yields2);
        out.println("Hedge ratio: " + DF.format(hedgeRatio));

        hedgeRatio = HedgeRatio.hedgeRatio(yields2, yields);
        out.println("Hedge ratio conversely: " + DF.format(hedgeRatio));
        log.info("Task2 finished");
    }
}

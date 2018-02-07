package com.company.task;

import com.company.statistic.ExpectedShortfall;
import com.company.statistic.Statistic;
import com.company.statistic.VAR;
import com.company.statistic.Yield;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;
import java.util.List;

import static com.company.FinansysApplication.DF;

@Slf4j
public class Task1 {
    public static void run(List<Double> yields, List<Double> yields2,
                           PrintStream outForYields, PrintStream out) {
        log.info("Task1 is executing");
        // yields
        outForYields.println("Yields:");
        for (Double yield : yields) {
            outForYields.println(DF.format(yield));
        }

        // average yield, volatility
        double averageYield = Yield.averageYield(yields);
        out.println("Average yield: " + DF.format(averageYield));
        double volatility = Statistic.standardDeviation(yields);
        out.println("\nVolatility: " + DF.format(volatility));

        // historical var
        double historicalVAR99percent = VAR.historicalVAR(yields, 251, 0.01);
        out.println("\nHistorical VAR for 251 days, 1%: " + DF.format(historicalVAR99percent));
        double historicalVAR95percent = VAR.historicalVAR(yields, 1255, 0.05);
        out.println("\nHistorical VAR for 1255 days, 5%: " + DF.format(historicalVAR95percent));

        // 1 day parametric var
        double parametric1dayVAR99percent = VAR.parametric99VAR(yields, 1);
        out.println("\nParametric 1 day VAR, 1%: " + DF.format(parametric1dayVAR99percent));
        double parametric1dayVAR95percent = VAR.parametric95VAR(yields, 1);
        out.println("\nParametric 1 day VAR, 5%: " + DF.format(parametric1dayVAR95percent));

        // 10 days parametric var
        double parametric10dayVAR99percent = VAR.parametric99VAR(yields, 10);
        out.println("\nParametric 10 days VAR, 1%: " + DF.format(parametric10dayVAR99percent));
        double parametric10dayVAR95percent = VAR.parametric95VAR(yields, 10);
        out.println("\nParametric 10 days VAR, 5%: " + DF.format(parametric10dayVAR95percent));

        // expected shortfall
        double expectedShortfall = ExpectedShortfall.expectedShortfall(yields, 1255, 0.05);
        out.println("\nExpected shortfall, 5%: " + DF.format(expectedShortfall));

        // correlation
        out.println("\nCorrelation: " + DF.format(Statistic.correlation(yields, yields2)));
        log.info("Task1 finished");
    }
}

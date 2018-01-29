package com.company.statistic;

import com.company.model.Security;

import java.util.List;

public class Risk {
    public double standardDeviation(List<Security> securities) {
        return Math.pow(dispersion(securities), 0.5);
    }

    public double dispersion(List<Security> securities) {
        List<Double> yields = Yield.yields(securities);
        double averageYield = Yield.averageYield(yields);
        return yields.stream()
                .mapToDouble(y -> Math.pow(y - averageYield, 2.0))
                .average().orElse(0.0);
    }
}

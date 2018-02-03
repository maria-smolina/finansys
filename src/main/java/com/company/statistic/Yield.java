package com.company.statistic;

import com.company.model.Security;
import com.company.service.Checker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Yield {
    public static double averageYieldForSecurities(List<Security> securities) {
        List<Double> yields = yields(securities);
        return averageYield(yields);
    }

    public static double averageYield(List<Double> yields) {
        if (yields.size() == 0) {
            throw new IllegalArgumentException("Empty securities list");
        }
        return yields.stream().mapToDouble(t -> t).average().orElse(0.0);
    }

    public static double yield(Security s1, Security s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Price is null");
        }
        Checker.checkPriceRange(s1.getPrice());
        Checker.checkPriceRange(s2.getPrice());
        return s2.getPrice() / s1.getPrice() - 1;
    }

    public static List<Double> yields(List<Security> securities) {
        return IntStream.range(1, securities.size())
                .mapToObj(i -> yield(securities.get(i - 1), securities.get(i)))
                .collect(Collectors.toList());
    }
}

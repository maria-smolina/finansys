package com.company.statistic;

import com.company.model.Security;
import com.company.service.Checker;

import java.util.List;

public class Yield {
    public static double averageYield(List<Security> securities) {
        if (securities == null || securities.size() < 2) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 1; i < securities.size(); i++) {
            sum += yield(securities.get(i - 1), securities.get(i));
        }
        return sum / (securities.size() - 1);
    }

    public static double yield(Security s1, Security s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Price is null");
        }
        Checker.checkPriceRange(s1.getClose());
        Checker.checkPriceRange(s2.getClose());
        return s2.getClose() / s1.getClose() - 1;
    }

}

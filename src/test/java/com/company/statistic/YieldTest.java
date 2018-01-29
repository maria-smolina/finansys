package com.company.statistic;

import com.company.model.Security;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YieldTest {
    @Test
    public void averageYieldForSecuritiesTest() {
        List<Security> securities = new ArrayList<>();
        securities.add(new SecurityMock(135.74));
        securities.add(new SecurityMock(133.3781));
        securities.add(new SecurityMock(138.0));
        Assert.assertEquals(0.00862621928, Yield.averageYieldForSecurities(securities), 1E-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void averageYieldForOneSecurityTest() {
        List<Security> securities = new ArrayList<>();
        securities.add(new SecurityMock(135.74));
        Yield.averageYieldForSecurities(securities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void averageYieldForNoSecurityTest() {
        List<Security> securities = new ArrayList<>();
        Yield.averageYieldForSecurities(securities);
    }

    @Test
    public void averageYieldTest() {
        List<Double> yields = newArrayList(1.0, 2.0, 3.0, 4.0, 5.0);
        Assert.assertEquals(3.0, Yield.averageYield(yields), 0);
    }

    @Test
    public void averageOneYieldTest() {
        List<Double> yields = Collections.singletonList(178.67);
        Assert.assertEquals(178.67, Yield.averageYield(yields), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void averageNoYieldTest() {
        List<Double> yields = Collections.emptyList();
        Yield.averageYield(yields);
    }

    @Test
    public void yieldTest() {
        Security s1 = new SecurityMock(135.74);
        Security s2 = new SecurityMock(138.26);
        Assert.assertEquals(0.01856490349, Yield.yield(s1, s2), 1E-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void yieldZeroPriceTest() {
        Security s1 = new SecurityMock();
        Security s2 = new SecurityMock(138.26);
        Yield.yield(s1, s2);
    }

    @Test
    public void yieldNegativeTest() {
        Security s1 = new SecurityMock(182387.74);
        Security s2 = new SecurityMock(1837.26);
        Assert.assertEquals(-0.98992662555, Yield.yield(s1, s2), 1E-10);
    }

    private static class SecurityMock implements Security {
        private double close = 0.0;

        public SecurityMock() {
        }

        public SecurityMock(double close) {
            this.close = close;
        }

        @Override
        public double getClose() {
            return close;
        }
    }
}

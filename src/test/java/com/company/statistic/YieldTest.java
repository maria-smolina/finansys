package com.company.statistic;

import com.company.model.Security;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YieldTest {
    @Test
    public void averageYieldTest() {
        List<Security> securities = new ArrayList<>();
        securities.add(new SecurityMock(135.74));
        securities.add(new SecurityMock(133.3781));
        securities.add(new SecurityMock(138.0));
        Assert.assertEquals(0.00862621928, Yield.averageYield(securities), 1E-10);
    }

    @Test
    public void averageYieldOneSecurityTest() {
        List<Security> securities = new ArrayList<>();
        securities.add(new SecurityMock(135.74));
        Assert.assertEquals(0.0, Yield.averageYield(securities), 0);
    }

    @Test
    public void averageYieldNoSecurityTest() {
        List<Security> securities = new ArrayList<>();
        Assert.assertEquals(0.0, Yield.averageYield(securities), 0);
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

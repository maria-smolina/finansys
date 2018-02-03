package com.company.statistic;

import com.company.model.Security;
import com.company.model.SecurityMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskTest {
    @Test
    public void dispersionTest() {
        List<Security> securities = new ArrayList<>();
        securities.add(new SecurityMock(135.74));
        securities.add(new SecurityMock(133.3781));
        securities.add(new SecurityMock(138.0));
        List<Double> yeilds = Yield.yields(securities);
        Assert.assertEquals(0.00067737329, Risk.dispersion(yeilds), 1E-10);
    }
}

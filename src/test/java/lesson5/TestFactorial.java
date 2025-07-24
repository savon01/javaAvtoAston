package lesson5;

import jdk.jfr.Description;
import org.example.lesson12.FactorialNumber;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestFactorial {

    //Проверка нуля
    @Test
    @Description("Проверка нуля")
    public void testZeroFactorial() {
        int zeroFact = FactorialNumber.factorial(0);
        Assert.assertEquals(zeroFact, 1);
    }
    //Проверка 1
    @Test
    @Description("Проверка 1")
    public void testOneFactorial() {
        int zeroFact = FactorialNumber.factorial(1);
        Assert.assertEquals(zeroFact, 1);
    }
    //Проверка 5
    @Test
    @Description("Проверка 5")
    public void testFiveFactorial() {
        int zeroFact = FactorialNumber.factorial(5);
        Assert.assertEquals(zeroFact, 120);
    }

    //Проверка -3
    @Test(expectedExceptions = IllegalArgumentException.class)
    @Description("Проверка -3")
    public void testNegativeFactorial() {
         FactorialNumber.factorial(-3);
    }
}

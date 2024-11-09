package flik;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Fliktest {
    @Test
    public void test1(){
        int numberOne = 0;
        int numberTwo = 0;
        assertTrue(Flik.isSameNumber(numberOne, numberTwo));
        assertTrue(Flik.isSameNumber(numberTwo, numberOne));
    }

    @Test
    public void test2(){
        int numberOne = 128;
        int numberTwo = 128;
        assertTrue(Flik.isSameNumber(numberOne, numberTwo));
        assertTrue(Flik.isSameNumber(numberTwo, numberOne));
    }
}

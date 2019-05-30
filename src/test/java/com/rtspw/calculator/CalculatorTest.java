package com.rtspw.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void binaryFunctionCalculationTest() {
        assertEquals("5", Calculator.parseInfixEquation("2 + 3"));
        assertEquals("-1", Calculator.parseInfixEquation("2 - 3"));
        assertEquals("6", Calculator.parseInfixEquation("2 * 3"));
        assertEquals("2.5", Calculator.parseInfixEquation("5 / 2"));
        assertEquals("8", Calculator.parseInfixEquation("2^(3)"));
        assertEquals("0.5", Calculator.parseInfixEquation("1 / 2^(66.5 - 65.5)"));
    }

    @Test
    public void unaryFunctionCalculationTest() {
        assertEquals(String.format("%.10f", Math.sin(3)), Calculator.parseInfixEquation("sin(3)"));
        assertEquals(String.format("%.10f", Math.sin(Math.cos(3))), Calculator.parseInfixEquation("sin(cos(3))"));
        assertEquals(String.format("%.10f", Math.log(2 * 3) / Math.tan(2 * 4)),
                Calculator.parseInfixEquation("log(2 * 3) / tan(2 * 4)"));
        assertEquals("2", Calculator.parseInfixEquation("sqrt(4)"));
    }

}

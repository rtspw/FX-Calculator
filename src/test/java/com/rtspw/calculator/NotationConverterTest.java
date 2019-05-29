package com.rtspw.calculator;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class NotationConverterTest {

    @Test
    public void operatorConversionTest() {
        assertEquals(NotationConverter.UTF16ToASCIIOperators("3 ÷ 2 × ±1"), "3 / 2 * -1");
    }

    @Test
    @Ignore("Still in progress")
    public void BasicOperatorsTest() {
        assertEquals(NotationConverter.infixToPostfix("3 + 2"), "3 2 +");
        assertEquals(NotationConverter.infixToPostfix("2 + 3"), "2 3 +");
        assertEquals(NotationConverter.infixToPostfix("(3) + (2)"), "3 2 +");
        assertEquals(NotationConverter.infixToPostfix("(3 + 2)"), "3 2 +");
        assertEquals(NotationConverter.infixToPostfix("5 - 4 + 3"), "5 4 - 3 +");
        assertEquals(NotationConverter.infixToPostfix("3 ÷ 2 ÷ 1"), "3 2 ÷ 1 ÷");
        assertEquals(NotationConverter.infixToPostfix("3 ÷ (2 × 1)"), "3 2 1 × ÷");
        assertEquals(NotationConverter.infixToPostfix("(3 ÷ 2) × 1"), "3 2 ÷ 1 ×");
    }

    @Test
    @Ignore("Still in progress")
    public void NegativeNumbersTest() {
        assertEquals(NotationConverter.infixToPostfix("3 + -2"), "3 -2 +");
        assertEquals(NotationConverter.infixToPostfix("-3 - -2"), "-3 -2 -");
    }

    @Test
    @Ignore("Still in progress")
    public void orderOfOperationsTest() {
        assertEquals(NotationConverter.infixToPostfix("3 + 2 × 4"), "3 2 4 × +");
        assertEquals(NotationConverter.infixToPostfix("3 × 2 × 4"), "3 2 × 4 ×");
        assertEquals(NotationConverter.infixToPostfix("3 × 2 + 4"), "3 2 × 4 +");
        assertEquals(NotationConverter.infixToPostfix("1 × 2^(3 + 4)"), "1 2 3 4 + ^ ×");
    }

    @Test
    @Ignore("Still in progress")
    public void unaryFunctionTest() {
        assertEquals(NotationConverter.infixToPostfix("sin(3)"), "3 sin");
        assertEquals(NotationConverter.infixToPostfix("2 - cos(5 + 3)"), "2 5 3 + cos -");

    }
}

package com.rtspw.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class NotationConverterTest {

    @Test
    public void operatorConversionTest() {
        assertEquals("3 / 2 * -1", NotationConverter.UTF16ToASCIIOperators("3 ÷ 2 × ±1"));
    }

    @Test
    public void BasicOperatorsTest() {
        assertEquals("3 2 +", NotationConverter.infixToPostfix("3 + 2"));
        assertEquals("2 3 +", NotationConverter.infixToPostfix("2 + 3"));
        assertEquals("3 2 +", NotationConverter.infixToPostfix("(3) + (2)"));
        assertEquals("3 2 +", NotationConverter.infixToPostfix("(3 + 2)"));
        assertEquals("5 4 - 3 +", NotationConverter.infixToPostfix("5 - 4 + 3"));
        assertEquals("3 2 / 1 /", NotationConverter.infixToPostfix("3 / 2 / 1"));
        assertEquals("3 2 1 * /", NotationConverter.infixToPostfix("3 / (2 * 1)"));
        assertEquals("3 2 / 1 *", NotationConverter.infixToPostfix("(3 / 2) * 1"));
    }

    @Test
    public void NegativeNumbersTest() {
        Arrays.stream(EquationTokenizer.tokenizeEquation("3 + -2")).forEach(System.out::println);
        assertEquals("3 -2 +", NotationConverter.infixToPostfix("3 + -2"));
        assertEquals("-3 -2 -", NotationConverter.infixToPostfix("-3 - -2"));
    }

    @Test
    public void orderOfOperationsTest() {
        assertEquals("3 2 4 * +", NotationConverter.infixToPostfix("3 + 2 * 4"));
        assertEquals("3 2 * 4 *", NotationConverter.infixToPostfix("3 * 2 * 4"));
        assertEquals("3 2 * 4 +", NotationConverter.infixToPostfix("3 * 2 + 4"));
        assertEquals("1 2 3 4 + ^ *", NotationConverter.infixToPostfix("1 * 2^(3 + 4)"));
    }

    @Test
    public void unaryFunctionTest() {
        assertEquals("3 sin", NotationConverter.infixToPostfix("sin(3)"));
        assertEquals("2 5 3 + cos -", NotationConverter.infixToPostfix("2 - cos(5 + 3)"));
    }
}

package com.rtspw.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokenIdentifierTest {

    @Test
    public void isOperatorTest() {
        TokenIdentifier token = new TokenIdentifier("+");
        assertTrue(token.isOperator());
        assertFalse(token.hasDot());
        assertFalse(token.isFunction());
        assertFalse(token.isNumeral());
        assertFalse(token.hasLeftParentheses());
        assertFalse(token.hasRightParentheses());
        assertFalse(token.isPowFunction());
    }

    @Test
    public void isNumeralTest() {
        TokenIdentifier token = new TokenIdentifier("1");
        assertTrue(token.isNumeral());
        assertFalse(token.hasDot());
        assertFalse(token.isFunction());
        assertFalse(token.isOperator());
        assertFalse(token.hasLeftParentheses());
        assertFalse(token.hasRightParentheses());
        assertFalse(token.isPowFunction());
    }

    @Test
    public void isNumberTest() {
        TokenIdentifier token = new TokenIdentifier(".23");
        assertTrue(token.hasDot());
        assertTrue(token.isNumber());
        assertFalse(token.isNumeral());
        assertFalse(token.isFunction());
        assertFalse(token.isOperator());
        assertFalse(token.hasLeftParentheses());
        assertFalse(token.hasRightParentheses());
        assertFalse(token.isPowFunction());
    }

    @Test
    public void hasDecimalTest() {
        TokenIdentifier token = new TokenIdentifier("12.567");
        assertTrue(token.hasDot());
        assertTrue(token.isNumber());
        assertFalse(token.isNumeral());
        assertFalse(token.isFunction());
        assertFalse(token.isOperator());
        assertFalse(token.hasLeftParentheses());
        assertFalse(token.hasRightParentheses());
        assertFalse(token.isPowFunction());
    }

    @Test
    public void functionTest() {
        TokenIdentifier token = new TokenIdentifier("sin(");
        assertTrue(token.isFunction());
        assertTrue(token.hasLeftParentheses());
        assertFalse(token.isNumeral());
        assertFalse(token.hasDot());
        assertFalse(token.isOperator());
        assertFalse(token.hasRightParentheses());
        assertFalse(token.isPowFunction());
    }

    @Test
    public void powerFunctionTest() {
        TokenIdentifier token = new TokenIdentifier("3^(");
        assertTrue(token.hasLeftParentheses());
        assertTrue(token.isPowFunction());
        assertFalse(token.hasRightParentheses());
        assertFalse(token.isNumeral());
        assertFalse(token.isOperator());
        assertFalse(token.hasDot());
    }

    @Test
    public void powerFunctionTest2() {
        TokenIdentifier token = new TokenIdentifier("3^(2.3)");
        assertTrue(token.hasLeftParentheses());
        assertTrue(token.hasRightParentheses());
        assertTrue(token.hasDot());
        assertFalse(token.isPowFunction());
        assertFalse(token.isNumeral());
        assertFalse(token.isOperator());
    }

}

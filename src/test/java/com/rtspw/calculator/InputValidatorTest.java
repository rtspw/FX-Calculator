package com.rtspw.calculator;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputValidatorTest {

    private InputValidator iv;

    @Before
    public void setUpInputValidator() {
        iv = new InputValidator();
    }

    @Test
    public void parenthesesMatchingTest() {
        iv.addToken("2").addToken("+").addToken("3");
        assertFalse(iv.isValid(")"));
    }

    @Test
    public void parenthesesMatchingTest2() {
        iv.addToken("(").addToken("2").addToken("+").addToken("3");
        assertTrue(iv.isValid(")"));
    }

    @Test
    public void noOperatorsAfterLeftParenthesesTest() {
        iv.addToken("(");
        assertFalse(iv.isValid("-"));
        iv.addToken("2");
        assertTrue(iv.isValid("-"));
    }

    @Test
    public void noOperatorsBeforeRightParenthesesTest() {
        iv.addToken("(").addToken("3").addToken("+");
        assertFalse(iv.isValid(")"));
        iv.addToken("7");
        assertTrue(iv.isValid(")"));
    }

    @Test
    public void noEmptyParenthesesTest() {
        iv.addToken("(");
        assertFalse(iv.isValid(")"));
        iv.addToken("1");
        assertTrue(iv.isValid(")"));
    }

    @Test
    public void noLeftParenthesesAfterRightParenthesesTest() {
        iv.addToken("(").addToken("3").addToken("-").addToken("2").addToken(")");
        assertFalse(iv.isValid("("));
    }

    @Test
    public void noInitialOperatorsTest() {
        assertFalse(iv.isValid("÷"));
        assertFalse(iv.isValid("×"));
        assertFalse(iv.isValid("-"));
        assertFalse(iv.isValid("+"));
    }

    @Test
    public void noDoubleOperatorsTest() {
        iv.addToken("-");
        assertFalse(iv.isValid("÷"));
        assertFalse(iv.isValid("×"));
        assertFalse(iv.isValid("-"));
        assertFalse(iv.isValid("+"));
    }

//    @Test
//    public void negativeNumbersTest() {
//        assertTrue(iv.isValid("±"));
//        iv.addToken("±");
//        assertFalse(iv.isValid("±"));
//        assertFalse(iv.isValid("-"));
//        assertFalse(iv.isValid(")"));
//        assertTrue(iv.isValid("2"));
//    }

    @Test
    public void unaryFunctionTest() {
        iv.addToken("sin(");
        assertFalse(iv.isValid(")"));
    }

    @Test
    public void decimalPointTest() {
        assertTrue(iv.isValid("."));
        iv.addToken(".").addToken("2");
        assertFalse(iv.isValid("."));
    }

    @Test
    public void decimalPointTest2() {
        iv.addToken("3").addToken("2");
        assertTrue(iv.isValid("."));
        iv.addToken(".").addToken("4").addToken("5");
        assertFalse(iv.isValid("."));
        iv.addToken("+");
        assertTrue(iv.isValid("."));
        iv.addToken(".").addToken("2");
        assertFalse(iv.isValid("."));
    }
}

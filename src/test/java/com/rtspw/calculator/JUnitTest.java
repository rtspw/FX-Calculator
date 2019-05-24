package com.rtspw.calculator;


import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitTest {

    @Test
    public void hey() {
        InputValidator hi = new InputValidator();
        hi.addToken("2");
        hi.addToken("+");
        hi.addToken("3");
        assertFalse(hi.isValid(")"));
    }
}

package com.rtspw.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class EquationTokenizerTest {

    @Test
    public void basicTest() {
        String[] expected = {"3", "-", "2", "+", "(", "1", "/", "23.4", ")"};
        assertArrayEquals(expected, EquationTokenizer.tokenizeEquation("3 - 2 + (1 / 23.4)"));
    }

    @Test
    public void functionTest() {
        String[] expected = {"sin", "(", "3", "-", "(", "2", ")", "+", ".24", ")", "*", "log", "(", "9", ")"};
        assertArrayEquals(expected, EquationTokenizer.tokenizeEquation("sin(3 - (2) + .24) * log(9)"));
    }

    @Test
    public void powerTest() {
        String[] expected = {"2", "^", "(", "4", ")"};
        assertArrayEquals(expected, EquationTokenizer.tokenizeEquation("2^(4)"));
        String[] expected2 = {"2", "^", "(", "4", "^", "(", ".2", ")", ")"};
        assertArrayEquals(expected2, EquationTokenizer.tokenizeEquation("2^(4^(.2))"));
    }

    @Test
    public void negativeTest() {
        String[] expected = {"-2", "-", "-3"};
        assertArrayEquals(expected, EquationTokenizer.tokenizeEquation("-2 - -3"));
    }
}

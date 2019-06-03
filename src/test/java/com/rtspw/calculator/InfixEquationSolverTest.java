package com.rtspw.calculator;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InfixEquationSolverTest {

    private static EquationSolver infixEquationSolver;

    @BeforeClass
    public static void setUpEquationSolver() {
        infixEquationSolver = new InfixEquationSolver();
    }

    @Test
    public void binaryFunctionCalculationTest() {
        assertEquals("5", infixEquationSolver.solveEquation("2 + 3"));
        assertEquals("-1", infixEquationSolver.solveEquation("2 - 3"));
        assertEquals("6", infixEquationSolver.solveEquation("2 * 3"));
        assertEquals("2.5", infixEquationSolver.solveEquation("5 / 2"));
        assertEquals("8", infixEquationSolver.solveEquation("2^(3)"));
        assertEquals("0.5", infixEquationSolver.solveEquation("1 / 2^(66.5 - 65.5)"));
    }

    @Test
    public void unaryFunctionCalculationTest() {
        assertEquals(String.format("%.10f", Math.sin(3)), infixEquationSolver.solveEquation("sin(3)"));
        assertEquals(String.format("%.10f", Math.sin(Math.cos(3))), infixEquationSolver.solveEquation("sin(cos(3))"));
        assertEquals(String.format("%.10f", Math.log(2 * 3) / Math.tan(2 * 4)),
                infixEquationSolver.solveEquation("log(2 * 3) / tan(2 * 4)"));
        assertEquals("2", infixEquationSolver.solveEquation("sqrt(4)"));
    }

}

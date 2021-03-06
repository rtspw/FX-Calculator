package com.rtspw.calculator;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class InfixEquationSolver extends EquationSolver {

    static private HashMap<String, BinaryOperator<Double>> operatorTokenToBinaryFunctionMap;
    static private HashMap<String, UnaryOperator<Double>> operatorTokenToUnaryFunctionMap;
    static private HashMap<String, Integer> operatorArityMap;
    static private DecimalFormat formatter;

    static {
        operatorTokenToBinaryFunctionMap = new HashMap<>();
        operatorTokenToBinaryFunctionMap.put("+", Double::sum);
        operatorTokenToBinaryFunctionMap.put("-", (x, y) -> x - y);
        operatorTokenToBinaryFunctionMap.put("*", (x, y) -> x * y);
        operatorTokenToBinaryFunctionMap.put("/", (x, y) -> x / y);
        operatorTokenToBinaryFunctionMap.put("^", Math::pow);

        operatorTokenToUnaryFunctionMap = new HashMap<>();
        operatorTokenToUnaryFunctionMap.put("sin", Math::sin);
        operatorTokenToUnaryFunctionMap.put("cos", Math::cos);
        operatorTokenToUnaryFunctionMap.put("tan", Math::tan);
        operatorTokenToUnaryFunctionMap.put("log", Math::log);
        operatorTokenToUnaryFunctionMap.put("sqrt", Math::sqrt);

        operatorArityMap = new HashMap<>();
        operatorArityMap.put("+", 2);
        operatorArityMap.put("-", 2);
        operatorArityMap.put("*", 2);
        operatorArityMap.put("/", 2);
        operatorArityMap.put("^", 2);
        operatorArityMap.put("sin", 1);
        operatorArityMap.put("cos", 1);
        operatorArityMap.put("tan", 1);
        operatorArityMap.put("log", 1);
        operatorArityMap.put("sqrt", 1);

        formatter = new DecimalFormat();
        formatter.setDecimalSeparatorAlwaysShown(false);
        formatter.setMaximumFractionDigits(25);
        formatter.setGroupingUsed(false);
    }

    @Override
    String solveEquation(String equation) {
        String rpn = NotationConverter.infixToPostfix(equation);
        String[] tokens = rpn.split(" ");
        Stack<Double> equationParsingStack = new Stack<>();

        Arrays.stream(tokens).forEach(token -> {
            TokenIdentifier current = new TokenIdentifier(token);
            if (current.isNumber() || token.equals("NaN")) {
                equationParsingStack.push(Double.parseDouble(token));
                return;
            }

            int operatorArity = operatorArityMap.get(token);
            if (operatorArity == 1) {
                UnaryOperator<Double> op = operatorTokenToUnaryFunctionMap.get(token);
                Double num = equationParsingStack.pop();
                equationParsingStack.push(op.apply(num));
            } else if (operatorArity == 2) {
                BinaryOperator<Double> op = operatorTokenToBinaryFunctionMap.get(token);
                Double num2 = equationParsingStack.pop();
                Double num1 = equationParsingStack.pop();
                equationParsingStack.push(op.apply(num1, num2));
            }
        });

        return formatter.format(equationParsingStack.pop());
    }

}

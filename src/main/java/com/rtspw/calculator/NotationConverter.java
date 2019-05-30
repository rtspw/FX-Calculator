package com.rtspw.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;

class NotationConverter {

    private enum Associativity { LEFT, RIGHT }
    private static final HashMap<Character, Character> operatorConversionMap;
    private static final HashMap<String, Integer> operatorPrecedenceMap;
    private static final HashMap<String, Associativity> operatorAssociativityMap;
    static {
        operatorConversionMap = new HashMap<>();
        operatorConversionMap.put('×', '*');
        operatorConversionMap.put('÷', '/');
        operatorConversionMap.put('±', '-');

        operatorPrecedenceMap = new HashMap<>();
        operatorPrecedenceMap.put("^", 4);
        operatorPrecedenceMap.put("*", 3);
        operatorPrecedenceMap.put("/", 3);
        operatorPrecedenceMap.put("+", 2);
        operatorPrecedenceMap.put("-", 2);

        operatorAssociativityMap = new HashMap<>();
        operatorAssociativityMap.put("^", Associativity.RIGHT);
        operatorAssociativityMap.put("*", Associativity.LEFT);
        operatorAssociativityMap.put("/", Associativity.LEFT);
        operatorAssociativityMap.put("+", Associativity.LEFT);
        operatorAssociativityMap.put("-", Associativity.LEFT);
    }

    static private int getPrecedence(TokenIdentifier operatorToken) {
        return operatorPrecedenceMap.getOrDefault(operatorToken.getToken(), 0);
    }

    static private Associativity getAssociativity(TokenIdentifier operatorToken) {
        return operatorAssociativityMap.getOrDefault(operatorToken.getToken(), Associativity.LEFT);
    }

    static String UTF16ToASCIIOperators(String equation) {
        return equation.codePoints()
                .mapToObj(c -> (char)c)
                .map(c -> operatorConversionMap.getOrDefault(c, c))
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    static String infixToPostfix(String equation) {
        String ASCIIEquation = UTF16ToASCIIOperators(equation);
        String[] equationItems = EquationTokenizer.tokenizeEquation(ASCIIEquation);

        ArrayList<String> outputList = new ArrayList<>();
        Stack<TokenIdentifier> operatorStack = new Stack<>();

        Arrays.stream(equationItems).forEach(item -> {
            TokenIdentifier current = new TokenIdentifier(item);
            if (current.isNumber()) {
                outputList.add(item);
            } else if (current.isFunction()) {
                operatorStack.push(current);
            } else if (current.isOperator()) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().hasLeftParentheses()
                        && (operatorStack.peek().isFunction()
                        || getPrecedence(operatorStack.peek()) > getPrecedence(current)
                        || getPrecedence(operatorStack.peek()) == getPrecedence(current)
                        && getAssociativity(operatorStack.peek()) == Associativity.LEFT)) {
                    outputList.add(operatorStack.pop().getToken());
                }
                operatorStack.push(current);
            } else if (current.hasLeftParentheses()) {
                operatorStack.push(current);
            } else if (current.hasRightParentheses()) {
                while (!operatorStack.peek().hasLeftParentheses()) {
                    outputList.add(operatorStack.pop().getToken());
                }
                if (operatorStack.peek().hasLeftParentheses()) {
                    operatorStack.pop();
                }
            }
        });

        while (!operatorStack.isEmpty()) {
            outputList.add(operatorStack.pop().getToken());
        }

        return String.join(" ", outputList);
    }

}

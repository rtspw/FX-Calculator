package com.rtspw.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;

class NotationConverter {

    private static enum Associativity { LEFT, RIGHT }
    private static final HashMap<Character, Character> operatorConversionMap;
    private static final HashMap<Character, Integer> operatorPrecedenceMap;
    private static final HashMap<Character, Associativity> operatorAssociativityMap;
    static {
        operatorConversionMap = new HashMap<>();
        operatorConversionMap.put('×', '*');
        operatorConversionMap.put('÷', '/');
        operatorConversionMap.put('±', '-');

        operatorPrecedenceMap = new HashMap<>();
        operatorPrecedenceMap.put('^', 4);
        operatorPrecedenceMap.put('*', 3);
        operatorPrecedenceMap.put('/', 3);
        operatorPrecedenceMap.put('+', 2);
        operatorPrecedenceMap.put('-', 2);

        operatorAssociativityMap = new HashMap<>();
        operatorAssociativityMap.put('^', Associativity.RIGHT);
        operatorAssociativityMap.put('*', Associativity.LEFT);
        operatorAssociativityMap.put('/', Associativity.LEFT);
        operatorAssociativityMap.put('+', Associativity.LEFT);
        operatorAssociativityMap.put('-', Associativity.LEFT);
    }

    static String UTF16ToASCIIOperators(String equation) {
        return equation.codePoints()
                .mapToObj(c -> (char)c)
                .map(c -> operatorConversionMap.getOrDefault(c, c))
                .map(Object::toString)
                .collect(Collectors.joining());

    }

    static String infixToPostfix(String equation) {
        StringBuilder rpn = new StringBuilder();
        String[] equationItems = EquationTokenizer.tokenizeEquation(equation);
        ArrayList<String> outputList = new ArrayList<>();
        Stack<TokenIdentifier> operatorStack = new Stack<>();
        Arrays.stream(equationItems).forEach(item -> {
            System.out.println("Considering " + item);
            System.out.println(outputList + " " + operatorStack);
            TokenIdentifier current = new TokenIdentifier(item);
            if (current.isNumber()) {
                outputList.add(item);
            } else if (current.isFunction()) {
                operatorStack.push(current);
            } else if (current.isOperator()) {
                while (!operatorStack.isEmpty() && (operatorStack.peek().isFunction()
                        || operatorPrecedenceMap.getOrDefault(operatorStack.peek().getToken(), 0) > operatorPrecedenceMap.getOrDefault(item, 0)
                        || (operatorPrecedenceMap.getOrDefault(operatorStack.peek().getToken(), 0).intValue() == operatorPrecedenceMap.getOrDefault(item, 0).intValue()
                            && operatorAssociativityMap.getOrDefault(operatorStack.peek().getToken(), Associativity.LEFT) == Associativity.LEFT)
                    ) && !operatorStack.peek().hasLeftParentheses()
                ) {
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

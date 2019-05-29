package com.rtspw.calculator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

class NotationConverter {

    private static final HashMap<Character, Character> operatorConversionMap;
    private static final HashMap<Character, Integer> operatorAssociativityMap;
    static {
        operatorConversionMap = new HashMap<>();
        operatorConversionMap.put('×', '*');
        operatorConversionMap.put('÷', '/');
        operatorConversionMap.put('±', '-');
        operatorAssociativityMap = new HashMap<>();
        operatorAssociativityMap.put('^', 4);
        operatorAssociativityMap.put('*', 3);
        operatorAssociativityMap.put('/', 3);
        operatorAssociativityMap.put('+', 2);
        operatorAssociativityMap.put('-', 2);
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
        Arrays.stream(equationItems).forEach(System.out::println);
        return "";
    }

}

package com.rtspw.calculator;

import java.util.HashMap;
import java.util.stream.Collectors;

class NotationConverter {

    private static final HashMap<Character, Character> operatorMap;
    static {
        operatorMap = new HashMap<>();
        operatorMap.put('×', '*');
        operatorMap.put('÷', '/');
        operatorMap.put('±', '-');

    }

    static String infixToPostfix(String equation) {
        return "";
    }

    static String UTF16ToASCIIOperators(String equation) {
        return equation.codePoints()
                .mapToObj(c -> (char)c)
                .map(c -> operatorMap.getOrDefault(c, c))
                .map(Object::toString)
                .collect(Collectors.joining());

    }
}

package com.rtspw.calculator;

import java.util.Arrays;

class EquationTokenizer {
    static String[] tokenizeEquation(String equation) {
        final String delimitSpacesAndParenthesesButKeepParentheses =
            " |((?<=[(])|(?=[(]))|((?<=[)])|(?=[)]))|((?<=[\\^])|(?=[\\^]))";
        String[] equationTokens = equation.split(delimitSpacesAndParenthesesButKeepParentheses);
        Arrays.stream(removeEmptyTokens(equationTokens)).forEach(System.out::println);
        return removeEmptyTokens(equationTokens);
    }

    static private String[] removeEmptyTokens(String[] tokens) {
        return Arrays.stream(tokens)
            .filter(token -> !token.isEmpty())
            .toArray(String[]::new);
    }
}

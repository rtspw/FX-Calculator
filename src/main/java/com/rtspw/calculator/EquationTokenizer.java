package com.rtspw.calculator;

import java.util.Arrays;

class EquationTokenizer {
    static String[] tokenizeEquation(String equation) {
        final String delimitSpacesCaretAndParenthesesButKeepCaretAndParentheses =
            " |((?<=[(])|(?=[(]))|((?<=[)])|(?=[)]))|((?<=[\\^])|(?=[\\^]))";
        String[] equationTokens = equation.split(delimitSpacesCaretAndParenthesesButKeepCaretAndParentheses);
        return removeEmptyTokens(equationTokens);
    }

    static private String[] removeEmptyTokens(String[] tokens) {
        return Arrays.stream(tokens)
            .filter(token -> !token.isEmpty())
            .toArray(String[]::new);
    }
}

package com.rtspw.calculator;

import java.util.function.Predicate;

class TokenIdentifier {

    private String token;
    final static private char[] operators = {'+', '-', '×', '*', '/', '÷', '^'};

    TokenIdentifier(String token) {
        this.token = token;
    }

    String getToken() {
        return this.token;
    }

    boolean isOperator() {
        final Predicate<Character> isOperator = c -> {
            for(char op : operators) {
                if (c == op) return true;
            }
            return false;
        };
        return token.chars().mapToObj(c -> (char)c).allMatch(isOperator);
    }

    boolean isNumber() {
        if (token.isEmpty()) return false;
        if (token.charAt(0) == '-' && token.length() < 2) return false;
        return token.chars().allMatch(c -> Character.isDigit(c) || c == '.' || c == '-');
    }

    boolean isNumeral() {
        if (token.isEmpty()) return false;
        return token.chars().allMatch(Character::isDigit);
    }

    boolean hasLeftParentheses() {
        return token.contains("(");
    }

    boolean hasRightParentheses() {
        return token.contains(")");
    }

    boolean hasDot() {
        return token.contains(".");
    }

    boolean isFunction() {
        return token.length() >= 2 && token.chars().allMatch(c -> Character.isAlphabetic(c) || c == '(');
    }

    boolean isPowFunctionWithParentheses() {
        if (token.length() < 2) return false;
        return token.charAt(token.length() - 2) == '^';
    }

    boolean isSpecialNegativeSymbol() {
        return token.equals("±");
    }

}

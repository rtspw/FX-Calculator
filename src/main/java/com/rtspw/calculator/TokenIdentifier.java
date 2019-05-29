package com.rtspw.calculator;

import java.util.function.Predicate;

class TokenIdentifier {

    private String token;
    final static private char[] operators = {'+', '-', '×', '÷'};

    TokenIdentifier(String token) {
        this.token = token;
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
        return token.chars().allMatch(c -> Character.isDigit(c) || c == '.');
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
        return token.length() >= 2 && StringUtil.getLastChar(token) == '(';
    }

    boolean isPowFunction() {
        if (token.length() < 2) return false;
        return token.charAt(token.length() - 2) == '^';
    }

    // temporary debug function
    @Override
    public String toString() {
        String properties = "[";
        if (isOperator()) properties += "operator, ";
        if (isNumeral()) properties += "number, ";
        if (hasLeftParentheses()) properties += "lp, ";
        if (hasRightParentheses()) properties += "rp, ";
        if (isFunction()) properties += "function, ";
        if (isPowFunction()) properties += "pow, ";
        if (hasDot()) properties += "dot, ";
        properties += "]";
        return properties;
    }
}

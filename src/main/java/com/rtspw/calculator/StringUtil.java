package com.rtspw.calculator;

import java.util.function.Predicate;

class StringUtil {
    final static private char[] operators = {'+', '-', '×', '÷'};

    static boolean isNumber(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    static boolean isOperator(String str) {
        final Predicate<Character> isOperator = c -> {
            for(char op : operators) {
                if (c == op) return true;
            }
            return false;
        };
        return str.chars().mapToObj(c -> (char)c).allMatch(isOperator);
    }
}

package com.rtspw.calculator;

import java.util.function.Predicate;

class StringUtil {
    final static private char[] operators = {'+', '-', '×', '÷'};

    static boolean isNumber(String str) {
        if (str.isEmpty()) return false;
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

    static boolean isFunction(String str) {
        return str.length() >= 2 && getLastChar(str) == '(';
    }

    static char getLastChar(String str) {
        return str.charAt(str.length() - 1);
    }

    static String popChars(String str, int amount) {
        return str.substring(0, str.length() - (amount));
    }

    static String getLastWord(String str) {
        String trimmed = str.trim();
        return trimmed.substring(trimmed.lastIndexOf(" ") + 1);
    }
}

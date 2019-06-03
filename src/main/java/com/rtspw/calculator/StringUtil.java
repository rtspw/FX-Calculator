package com.rtspw.calculator;

class StringUtil {
    static String popChars(String str, int amount) {
        return str.substring(0, str.length() - (amount));
    }

    static String getLastWord(String str) {
        String trimmed = str.trim();
        return trimmed.substring(trimmed.lastIndexOf(" ") + 1);
    }

    static String removeNonNumericChars(String str) {
        return str.replaceAll("[^0-9.]", "");
    }
}

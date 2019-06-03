package com.rtspw.calculator;

abstract class InputValidator {
    abstract boolean isValid(String input);
    abstract boolean isExpressionComplete();
    abstract InputValidator addToken(String token);
    abstract void removeToken();
    abstract void resetTokens();
}

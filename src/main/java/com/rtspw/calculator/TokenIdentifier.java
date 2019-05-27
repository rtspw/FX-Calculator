package com.rtspw.calculator;

class TokenIdentifier {

    private String token;
    private boolean isOperator;
    private boolean isNumber;
    private boolean isLeftParentheses;
    private boolean isRightParentheses;
    private boolean isFunction;

    TokenIdentifier(String token) {
        this.token = token;
        isOperator = StringUtil.isOperator(token);
        isNumber = StringUtil.isNumber(token);
        isLeftParentheses = StringUtil.isLeftParentheses(token);
        isRightParentheses = StringUtil.isRightParentheses(token);
        isFunction = StringUtil.isFunction(token);
    }

    String getToken() {
        return token;
    }

    boolean isOperator() {
        return isOperator;
    }

    boolean isNumber() {
        return isNumber;
    }

    boolean isLeftParentheses() {
        return isLeftParentheses;
    }

    boolean isRightParentheses() {
        return isRightParentheses;
    }

    boolean isFunction() {
        return isFunction;
    }
}

package com.rtspw.calculator;

class TokenIdentifier {

    private String token;
    private boolean isOperator;
    private boolean isNumber;
    private boolean isLeftParentheses;
    private boolean isRightParentheses;
    private boolean isFunction;
    private boolean isPowFunction;
    private boolean isDot;

    TokenIdentifier(String token) {
        this.token = token;
        isOperator = StringUtil.isOperator(token);
        isNumber = StringUtil.isNumber(token);
        isFunction = StringUtil.isFunction(token);
        isLeftParentheses = token.contains("(");
        isRightParentheses = token.contains(")");
        isPowFunction = token.contains("^");
        isDot = token.contains(".");
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

    boolean isDot() { return isDot; }

    boolean isFunction() {
        return isFunction;
    }

    boolean isPowFunction() { return isPowFunction; }

    // temporary debug function
    @Override
    public String toString() {
        String properties = "[";
        if (isOperator()) properties += "operator, ";
        if (isNumber()) properties += "number, ";
        if (isLeftParentheses()) properties += "lp, ";
        if (isRightParentheses()) properties += "rp, ";
        if (isFunction()) properties += "function, ";
        if (isPowFunction()) properties += "pow, ";
        if (isDot()) properties += "dot, ";
        properties += "]";
        return properties;
    }
}

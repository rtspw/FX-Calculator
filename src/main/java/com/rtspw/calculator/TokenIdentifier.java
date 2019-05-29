package com.rtspw.calculator;

class TokenIdentifier {

    private String token;

    TokenIdentifier(String token) {
        this.token = token;
    }

    boolean isOperator() {
        return StringUtil.isOperator(token);
    }

    boolean isNumber() {
        return StringUtil.isNumber(token);
    }

    boolean isLeftParentheses() {
        return token.contains("(");
    }

    boolean isRightParentheses() {
        return token.contains(")");
    }

    boolean isDot() {
        return token.contains(".");
    }

    boolean isFunction() {
        return StringUtil.isFunction(token);
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

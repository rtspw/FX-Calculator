package com.rtspw.calculator;

import java.util.Stack;

class InputValidator {

    private int parenthesesCount = 0;
    private Stack<String> tokens;
    final private char[] operators = {'+', '-', '×', '÷'};

    InputValidator() {
        this.tokens = new Stack<>();
        this.tokens.push("");
    }

    boolean isValid(String input) {

        final String prevToken = tokens.peek();
        final boolean currentIsNumber = StringUtil.isNumber(input);
        final boolean currentIsOperator = StringUtil.isOperator(input);
        final boolean currentIsLeftParen = StringUtil.isLeftParentheses(input);
        final boolean currentIsRightParen = StringUtil.isRightParentheses(input);
        final boolean prevIsNumber = StringUtil.isNumber(prevToken);
        final boolean prevIsOperator = StringUtil.isOperator(prevToken);

        if (currentIsLeftParen && prevIsNumber && !prevToken.equals("")) return false;
        if (currentIsRightParen && prevIsOperator) return false;
        if (currentIsOperator && prevToken.equals("")) return false;
        if (currentIsOperator && prevIsOperator) return false;

        if (input.equals("(")) {
            parenthesesCount += 1;
        } else if (input.equals(")")) {
            if (parenthesesCount == 0) return false;
            parenthesesCount -= 1;
        }

        return true;
    }

    void addToken(String token) {
        this.tokens.push(token);
    }

    void removeToken() {
        if (this.tokens.size() <= 1) return;
        String top = this.tokens.pop();
        if (top.equals("(")) parenthesesCount -= 1;
        if (top.equals(")")) parenthesesCount += 1;
    }

    void resetTokens() {
        this.tokens.clear();
        this.tokens.push("");
        this.parenthesesCount = 0;
    }
}

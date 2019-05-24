package com.rtspw.calculator;

import java.util.Stack;

class InputValidator {

    private int parenthesesCount = 0;
    final private Stack<String> tokens;

    InputValidator() {
        this.tokens = new Stack<>();
        this.tokens.push("");
    }

    boolean isValid(String input) {
        final String prevToken = tokens.peek();
        final TokenIdentifier current = new TokenIdentifier(input);
        final TokenIdentifier prev = new TokenIdentifier(prevToken);

        if (current.isLeftParentheses() && prev.isNumber() && !inputIsEmpty()) return false;
        if (current.isRightParentheses() && prev.isOperator()) return false;
        if (current.isRightParentheses() && prev.isLeftParentheses()) return false;
        if (current.isLeftParentheses() && prev.isRightParentheses()) return false;
        if (current.isNumber() && prev.isRightParentheses()) return false;
        if (current.isOperator() && prev.isLeftParentheses()) return false;
        if (current.isOperator() && inputIsEmpty()) return false;
        if (current.isOperator() && prev.isOperator()) return false;

        if (current.isLeftParentheses()) {
            parenthesesCount += 1;
        } else if (current.isRightParentheses()) {
            if (parenthesesCount == 0) return false;
            parenthesesCount -= 1;
        }

        return true;
    }

    boolean isExpressionComplete() {
        final String prevToken = tokens.peek();
        return StringUtil.isNumber(prevToken) || StringUtil.isRightParentheses(prevToken) && parenthesesCount == 0;
    }

    void addToken(String token) {
        this.tokens.push(token);
    }

    void removeToken() {
        if (this.tokens.size() <= 1) return;
        final String top = this.tokens.pop();
        if (StringUtil.isLeftParentheses(top)) parenthesesCount -= 1;
        if (StringUtil.isRightParentheses(top)) parenthesesCount += 1;
    }

    void resetTokens() {
        this.tokens.clear();
        this.tokens.push("");
        this.parenthesesCount = 0;
    }

    private boolean inputIsEmpty() {
        return tokens.size() == 1;
    }
}

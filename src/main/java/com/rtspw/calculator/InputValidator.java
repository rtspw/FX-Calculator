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
        final boolean currentIsNumber = StringUtil.isNumber(input);
        final boolean currentIsOperator = StringUtil.isOperator(input);
        final boolean currentIsLeftParen = StringUtil.isLeftParentheses(input);
        final boolean currentIsRightParen = StringUtil.isRightParentheses(input);
        final boolean prevIsNumber = StringUtil.isNumber(prevToken);
        final boolean prevIsOperator = StringUtil.isOperator(prevToken);
        final boolean prevIsLeftParen = StringUtil.isLeftParentheses(prevToken);
        final boolean prevIsRightParen = StringUtil.isRightParentheses(prevToken);

        if (currentIsLeftParen && prevIsNumber && tokens.size() != 1) return false;
        if (currentIsRightParen && prevIsOperator) return false;
        if (currentIsRightParen && prevIsLeftParen) return false;
        if (currentIsLeftParen && prevIsRightParen) return false;
        if (currentIsNumber && prevIsRightParen) return false;
        if (currentIsOperator && prevIsLeftParen) return false;
        if (currentIsOperator && tokens.size() == 1) return false;
        if (currentIsOperator && prevIsOperator) return false;

        if (currentIsLeftParen) {
            parenthesesCount += 1;
        } else if (currentIsRightParen) {
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
}

package com.rtspw.calculator;

import java.util.Stack;

class BasicInputValidator extends InputValidator {

    private int parenthesesCount = 0;
    final private Stack<String> tokens;

    BasicInputValidator() {
        this.tokens = new Stack<>();
        this.tokens.push("");
    }

    private boolean inputIsEmpty() {
        return tokens.size() == 1;
    }

    private boolean numberBlockAlreadyHasDot() {
        if (inputIsEmpty()) return false;

        final Stack<String> copiedTokens = new Stack<>();
        copiedTokens.addAll(tokens);

        String top;
        TokenIdentifier topInfo;
        do {
            top = copiedTokens.pop();
            topInfo = new TokenIdentifier(top);
            if (topInfo.hasDot()) return true;
        } while (topInfo.isNumeral());
        return false;
    }

    @Override
    boolean isValid(String input) {
        final String prevToken = tokens.peek();
        final TokenIdentifier current = new TokenIdentifier(input);
        final TokenIdentifier prev = new TokenIdentifier(prevToken);

        if (current.hasDot() && numberBlockAlreadyHasDot()) return false;
        if (current.hasDot() && prev.hasRightParentheses()) return false;
        if (current.hasRightParentheses() && parenthesesCount == 0) return false;
        if (current.hasRightParentheses() && prev.isOperator()) return false;
        if (current.hasRightParentheses() && prev.hasLeftParentheses()) return false;
        if (current.hasLeftParentheses() && !current.isPowFunctionWithParentheses() && prev.isNumeral() && !inputIsEmpty()) return false;
        if (current.hasLeftParentheses() && !current.isPowFunctionWithParentheses() && prev.hasRightParentheses()) return false;
        if (current.hasLeftParentheses() && prev.hasDot()) return false;
        if (current.isPowFunctionWithParentheses() && !prev.isNumeral() && !prev.hasRightParentheses()) return false;
        if (current.isNumeral() && prev.hasRightParentheses()) return false;
        if (current.isOperator() && prev.hasLeftParentheses()) return false;
        if (current.isOperator() && inputIsEmpty()) return false;
        if (current.isOperator() && prev.isOperator()) return false;
        if (!current.isNumber() && prev.isSpecialNegativeSymbol()) return false;
        if (current.isSpecialNegativeSymbol() && prev.isNumber()) return false;
        if (current.isSpecialNegativeSymbol() && prev.hasRightParentheses()) return false;

        return true;
    }

    @Override
    boolean isExpressionComplete() {
        final TokenIdentifier prevToken = new TokenIdentifier(tokens.peek());
        return (prevToken.isNumeral() || prevToken.hasRightParentheses()) && parenthesesCount == 0;
    }

    @Override
    BasicInputValidator addToken(String token) {
        this.tokens.push(token);
        final TokenIdentifier current = new TokenIdentifier(token);
        if (current.hasLeftParentheses()) parenthesesCount += 1;
        if (current.hasRightParentheses() && parenthesesCount != 0) parenthesesCount -= 1;
        return this;
    }

    @Override
    void removeToken() {
        if (this.tokens.size() <= 1) return;
        final String poppedToken = this.tokens.pop();
        final TokenIdentifier top = new TokenIdentifier(poppedToken);
        if (top.hasLeftParentheses()) parenthesesCount -= 1;
        if (top.hasRightParentheses()) parenthesesCount += 1;
    }

    @Override
    void resetTokens() {
        this.tokens.clear();
        this.tokens.push("");
        this.parenthesesCount = 0;
    }
}

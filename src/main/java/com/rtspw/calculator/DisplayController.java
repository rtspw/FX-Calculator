package com.rtspw.calculator;

import javafx.scene.control.Label;

class DisplayController {
    private Label display;
    private String text;

    private void updateDisplay() {
        display.setText(text);
    }

    private boolean lastTokenHasWhitespace() {
        return StringUtil.getLastChar(text) == ' ';
    }

    DisplayController(Label display) {
        this.display = display;
        text = "";
        updateDisplay();
    }

    void clear() {
        text = "";
        updateDisplay();
    }

    void append(String str) {
        text = text + str;
        updateDisplay();
    }

    void unappend() {
        if (text.length() == 0) return;
        TokenIdentifier lastToken = new TokenIdentifier(StringUtil.getLastWord(text));
        if (lastToken.isOperator())
            text = StringUtil.popChars(text, 3);
        else if (lastToken.isPowFunction())
            text = StringUtil.popChars(text, 2);
        else if (lastToken.isFunction())
            text = StringUtil.popChars(text, 4);
        else
            text = StringUtil.popChars(text, 1);
        updateDisplay();
    }
}
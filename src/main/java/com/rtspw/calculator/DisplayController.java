package com.rtspw.calculator;

import javafx.scene.control.Label;

class DisplayController {
    private Label display;
    private String text;

    private void updateDisplay() {
        display.setText(text);
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
        if (text.charAt(text.length() - 1) == ' ') {
            text = text.substring(0, text.length() - 1);
        }
        text = text.substring(0, text.length() - 1);
        updateDisplay();
    }
}
package com.rtspw.calculator;

import javafx.scene.control.Label;

class DisplayController {
    private Label display;
    private String text;

    private void updateDisplay() {
        this.display.setText(this.text);
    }

    DisplayController(Label display) {
        this.display = display;
        this.text = "";
        this.updateDisplay();
    }

    void clear() {
        this.text = "";
        this.updateDisplay();
    }

    void append(String str) {
        this.text = this.text + str;
        this.updateDisplay();
    }

    void unappend() {
        if (this.text.length() == 0) return;
        if (this.text.charAt(this.text.length() - 1) == ' ') {
            this.text = this.text.substring(0, this.text.length() - 1);
        }
        this.text = this.text.substring(0, this.text.length() - 1);
        this.updateDisplay();
    }
}
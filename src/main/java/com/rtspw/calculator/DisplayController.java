package com.rtspw.calculator;

import javafx.scene.control.Label;

class DisplayController {
  private Label display;
  private String text;

  private void updateText() {
    this.display.setText(this.text);
  }

  DisplayController(Label display) {
    this.display = display;
    this.text = "";
    this.updateText();
  }

  void clear() {
    this.text = "";
    this.updateText();
  }

  void append(String str) {
    this.text = this.text + str;
    this.updateText();
  }

  void unappend() {
    this.text = this.text.substring(0, this.text.length() - 1);
    this.updateText();
  }
}
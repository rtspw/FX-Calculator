package com.rtspw.calculator;

import javafx.scene.input.MouseEvent;

abstract class ButtonHandler {
    abstract void handleEvent(MouseEvent event);

    protected String addWhitespaceToOperators(String buttonText) {
        final TokenIdentifier text = new TokenIdentifier(buttonText);
        if (text.isOperator()) return " " + buttonText + " ";
        return buttonText;
    }
}

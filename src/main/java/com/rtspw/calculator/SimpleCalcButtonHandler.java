package com.rtspw.calculator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

class SimpleCalcButtonHandler extends ButtonHandler {

    private boolean invalidPreviousExpression;
    private InputValidator simpleInputValidator;
    private DisplayController displayController;
    private Button sourceButtonOfCurrentEvent;

    SimpleCalcButtonHandler(Label label) {
        simpleInputValidator = new InputValidator();
        displayController = new DisplayController(label);
    }

    @Override
    void handleEvent(MouseEvent event) {
        handlePreviousErrors();
        sourceButtonOfCurrentEvent = (Button)event.getSource();
        switch (getIDOfCurrentEvent()) {
            case "btnSimpleClear":
                handleClearButton();
                break;
            case "btnSimpleBackspace":
                handleBackspaceButton();
                break;
            case "btnSimplePlusMinus":
                handlePlusMinusButton();
                break;
            case "btnSimpleEquals":
                handleSimpleEqualsButtonAction();
                break;
            default:
                handleDefaultCase();
        }
    }

    private void handleClearButton() {
        displayController.clear();
        simpleInputValidator.resetTokens();
    }

    private void handleBackspaceButton() {
        displayController.unappend();
        simpleInputValidator.removeToken();
    }

    private void handlePlusMinusButton() {
        if (!simpleInputValidator.isValid("±")) return;
        simpleInputValidator.addToken("±");
        displayController.append("-");
    }

    private void handleDefaultCase() {
        String clickedButtonText = sourceButtonOfCurrentEvent.getText();
        if (!simpleInputValidator.isValid(clickedButtonText)) return;
        simpleInputValidator.addToken(clickedButtonText);
        displayController.append(addWhitespaceToOperators(clickedButtonText));
    }

    private String getIDOfCurrentEvent() {
        return sourceButtonOfCurrentEvent.getId();
    }

    private void handlePreviousErrors() {
        if (!invalidPreviousExpression) return;
        invalidPreviousExpression = false;
        displayController.clear();
        simpleInputValidator.resetTokens();
    }

    private void handleSimpleEqualsButtonAction() {
        if (!simpleInputValidator.isExpressionComplete()) return;
        simpleInputValidator.resetTokens();
        final String finalValue = Calculator.parseInfixEquation(displayController.getText());
        if (finalValue.equals("ERROR")) {
            invalidPreviousExpression = true;
        } else {
            finalValue.codePoints()
                    .mapToObj(c -> (char)c)
                    .map(c -> (c == '-') ? '±' : c)
                    .map(String::valueOf)
                    .forEach(simpleInputValidator::addToken);
        }
        displayController.clear();
        displayController.append(finalValue);
    }

    private String addWhitespaceToOperators(String buttonText) {
        final TokenIdentifier text = new TokenIdentifier(buttonText);
        if (text.isOperator()) return " " + buttonText + " ";
        return buttonText;
    }
}

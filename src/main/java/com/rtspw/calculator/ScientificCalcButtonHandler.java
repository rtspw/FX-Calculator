package com.rtspw.calculator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

class ScientificCalcButtonHandler extends ButtonHandler {

    private boolean invalidPreviousExpression;
    private DisplayController displayController;
    private InputValidator inputValidator;
    private Button sourceButtonOfCurrentEvent;

    ScientificCalcButtonHandler(Label label) {
        displayController = new DisplayController(label);
        inputValidator = new InputValidator();
    }

    @Override
    void handleEvent(MouseEvent event) {
        handlePreviousErrors();
        sourceButtonOfCurrentEvent = (Button)event.getSource();
        switch (getIDOfCurrentEvent()) {
            case "btnScientificClear":
                handleClearButton();
                break;
            case "btnScientificBackspace":
                handleBackspaceButton();
                break;
            case "btnScientificPlusMinus":
                handlePlusMinusButton();
                break;
            case "btnScientificEquals":
                handleScientificEqualsButtonAction();
                break;
            case "btnScientificSin":
            case "btnScientificCos":
            case "btnScientificTan":
            case "btnScientificLog":
            case "btnScientificPow":
            case "btnScientificSqrt":
                handleFunctionButton();
                break;
            default:
                handleDefaultCase();
        }
    }

    private void handleClearButton() {
        displayController.clear();
        inputValidator.resetTokens();
    }

    private void handleBackspaceButton() {
        displayController.unappend();
        inputValidator.removeToken();
    }

    private void handlePlusMinusButton() {
        if (!inputValidator.isValid("±")) return;
        inputValidator.addToken("±");
        displayController.append("-");
    }

    private void handleFunctionButton() {
        String functionText = sourceButtonOfCurrentEvent.getText() + "(";
        if (!inputValidator.isValid(functionText)) return;
        inputValidator.addToken(functionText);
        displayController.append(functionText);
    }

    private void handleDefaultCase() {
        String clickedButtonText = sourceButtonOfCurrentEvent.getText();
        if (!inputValidator.isValid(clickedButtonText)) return;
        inputValidator.addToken(clickedButtonText);
        displayController.append(addWhitespaceToOperators(clickedButtonText));
    }

    private void handlePreviousErrors() {
        if (!invalidPreviousExpression) return;
        invalidPreviousExpression = false;
        displayController.clear();
        inputValidator.resetTokens();
    }

    private String getIDOfCurrentEvent() {
        return sourceButtonOfCurrentEvent.getId();
    }

    private void handleScientificEqualsButtonAction() {
        if (!inputValidator.isExpressionComplete()) return;
        inputValidator.resetTokens();
        final String finalValue = Calculator.parseInfixEquation(displayController.getText());
        if (finalValue.equals("ERROR")) {
            invalidPreviousExpression = true;
        } else {
            finalValue.codePoints()
                    .mapToObj(c -> (char)c)
                    .map(c -> (c == '-') ? '±' : c)
                    .map(String::valueOf)
                    .forEach(inputValidator::addToken);
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

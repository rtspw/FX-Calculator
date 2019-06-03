package com.rtspw.calculator;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

class SimpleCalcButtonHandler extends ButtonHandler {

    private boolean invalidPreviousExpression;
    private InputValidator inputValidator;
    private DisplayController displayController;
    private EquationSolver equationSolver;
    private Button sourceButtonOfCurrentEvent;

    SimpleCalcButtonHandler(Label label, InputValidator inputValidator, EquationSolver equationSolver) {
        displayController = new DisplayController(label);
        this.inputValidator = inputValidator;
        this.equationSolver = equationSolver;
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

    private void handleDefaultCase() {
        String clickedButtonText = sourceButtonOfCurrentEvent.getText();
        if (!inputValidator.isValid(clickedButtonText)) return;
        inputValidator.addToken(clickedButtonText);
        displayController.append(super.addWhitespaceToOperators(clickedButtonText));
    }

    private String getIDOfCurrentEvent() {
        return sourceButtonOfCurrentEvent.getId();
    }

    private void handlePreviousErrors() {
        if (!invalidPreviousExpression) return;
        invalidPreviousExpression = false;
        displayController.clear();
        inputValidator.resetTokens();
    }

    private void handleSimpleEqualsButtonAction() {
        if (!inputValidator.isExpressionComplete()) return;
        inputValidator.resetTokens();
        final String finalValue = equationSolver.solveEquation(displayController.getText());
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

}

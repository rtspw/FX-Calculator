package com.rtspw.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CalculatorController {

    private DisplayController displayController;
    private InputValidator inputValidator;
    private boolean invalidPreviousExpression = false;

    @FXML
    private Label simpleCalcDisplay;

    @FXML
    private void initialize() {
        this.displayController = new DisplayController(simpleCalcDisplay);
        this.inputValidator = new InputValidator();
    }

    @FXML
    private void handleSimpleCalcButton(MouseEvent event) {
        Button sourceBtn = (Button)event.getSource();
        String sourceId = sourceBtn.getId();

        if (invalidPreviousExpression) {
            invalidPreviousExpression = false;
            displayController.clear();
            inputValidator.resetTokens();
        }

        switch (sourceId) {
            case "btnSimpleClear":
                displayController.clear();
                inputValidator.resetTokens();
                break;
            case "btnSimpleBackspace":
                displayController.unappend();
                inputValidator.removeToken();
                break;
            case "btnSimplePlusMinus":
                displayController.append("-");
                break;
            case "btnSimpleEquals":
                handleEqualsButtonAction();
                break;
            default:
                String clickedButtonText = sourceBtn.getText();
                if (inputValidator.isValid(clickedButtonText)) {
                    inputValidator.addToken(clickedButtonText);
                    displayController.append(addWhitespaceToOperators(clickedButtonText));
                }
        }
    }

    private void handleEqualsButtonAction() {
        if (!inputValidator.isExpressionComplete()) return;
        final String finalValue = "ERROR";
        if (finalValue.equals("ERROR")) {
            invalidPreviousExpression = true;
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
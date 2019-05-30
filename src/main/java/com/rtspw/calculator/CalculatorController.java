package com.rtspw.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CalculatorController {

    private DisplayController simpleDisplayController;
    private DisplayController scientificDisplayController;
    private InputValidator simpleInputValidator;
    private InputValidator scientificInputValidator;
    private boolean invalidPreviousSimpleExpression = false;
    private boolean invalidPreviousScientificExpression = false;

    @FXML
    private Label simpleCalcDisplay;

    @FXML
    private Label scientificCalcDisplay;

    @FXML
    private void initialize() {
        this.simpleDisplayController = new DisplayController(simpleCalcDisplay);
        this.scientificDisplayController = new DisplayController(scientificCalcDisplay);
        this.simpleInputValidator = new InputValidator();
        this.scientificInputValidator = new InputValidator();
    }

    @FXML
    private void handleSimpleCalcButton(MouseEvent event) {
        Button sourceBtn = (Button)event.getSource();
        String sourceId = sourceBtn.getId();

        if (invalidPreviousSimpleExpression) {
            invalidPreviousSimpleExpression = false;
            simpleDisplayController.clear();
            simpleInputValidator.resetTokens();
        }

        switch (sourceId) {
            case "btnSimpleClear":
                simpleDisplayController.clear();
                simpleInputValidator.resetTokens();
                break;
            case "btnSimpleBackspace":
                simpleDisplayController.unappend();
                simpleInputValidator.removeToken();
                break;
            case "btnSimplePlusMinus":
                if (simpleInputValidator.isValid("±")) {
                    simpleInputValidator.addToken("±");
                    simpleDisplayController.append("-");
                }
                break;
            case "btnSimpleEquals":
                handleSimpleEqualsButtonAction();
                break;
            default:
                String clickedButtonText = sourceBtn.getText();
                if (!simpleInputValidator.isValid(clickedButtonText)) return;
                simpleInputValidator.addToken(clickedButtonText);
                simpleDisplayController.append(addWhitespaceToOperators(clickedButtonText));

        }
    }

    private void handleSimpleEqualsButtonAction() {
        if (!simpleInputValidator.isExpressionComplete()) return;
        final String finalValue = Calculator.parseInfixEquation(simpleCalcDisplay.getText());
        if (finalValue.equals("ERROR")) {
            invalidPreviousSimpleExpression = true;
        } else {
            finalValue.codePoints()
                .mapToObj(c -> (char)c)
                .map(String::valueOf)
                .forEach(simpleInputValidator::addToken);
        }
        simpleDisplayController.clear();
        simpleDisplayController.append(finalValue);
    }

    @FXML
    private void handleScientificCalcButton(MouseEvent event) {
        Button sourceBtn = (Button)event.getSource();
        String sourceId = sourceBtn.getId();

        if (invalidPreviousScientificExpression) {
            invalidPreviousScientificExpression = false;
            scientificDisplayController.clear();
            scientificInputValidator.resetTokens();
        }

        switch (sourceId) {
            case "btnScientificClear":
                scientificDisplayController.clear();
                scientificInputValidator.resetTokens();
                break;
            case "btnScientificBackspace":
                scientificDisplayController.unappend();
                scientificInputValidator.removeToken();
                break;
            case "btnScientificPlusMinus":
                if (scientificInputValidator.isValid("±")) {
                    scientificInputValidator.addToken("±");
                    scientificDisplayController.append("-");
                }
                break;
            case "btnScientificEquals":
                handleScientificEqualsButtonAction();
                break;
            case "btnScientificSin":
            case "btnScientificCos":
            case "btnScientificTan":
            case "btnScientificLog":
            case "btnScientificPow":
                String functionText = sourceBtn.getText() + "(";
                if (scientificInputValidator.isValid(functionText)) {
                    scientificInputValidator.addToken(functionText);
                    scientificDisplayController.append(functionText);
                }
                break;
            default:
                String clickedButtonText = sourceBtn.getText();
                if (!scientificInputValidator.isValid(clickedButtonText)) return;
                scientificInputValidator.addToken(clickedButtonText);
                scientificDisplayController.append(addWhitespaceToOperators(clickedButtonText));
        }
    }

    private void handleScientificEqualsButtonAction() {
        if (!scientificInputValidator.isExpressionComplete()) return;
        scientificInputValidator.resetTokens();
        final String finalValue = Calculator.parseInfixEquation(scientificCalcDisplay.getText());
        if (finalValue.equals("ERROR")) {
            invalidPreviousScientificExpression = true;
        } else {
            finalValue.codePoints()
                .mapToObj(c -> (char)c)
                .map(String::valueOf)
                .forEach(scientificInputValidator::addToken);
        }
        scientificDisplayController.clear();
        scientificDisplayController.append(finalValue);
    }

    private String addWhitespaceToOperators(String buttonText) {
        final TokenIdentifier text = new TokenIdentifier(buttonText);
        if (text.isOperator()) return " " + buttonText + " ";
        return buttonText;
    }

}
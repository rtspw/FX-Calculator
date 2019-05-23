package com.rtspw.calculator;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CalculatorController {

    @FXML
    private Label simpleCalcDisplay;
    private DisplayController displayController;
    private InputValidator inputValidator;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void initialize() {
        this.displayController = new DisplayController(simpleCalcDisplay);
        this.inputValidator = new InputValidator();
    }

    @FXML
    private void handleSimpleCalcButton(MouseEvent event) {
        Button sourceBtn = (Button)event.getSource();
        String sourceId = sourceBtn.getId();

        switch (sourceId) {
            case "btnSimpleClear":
                displayController.clear();
                break;
            case "btnSimpleBackspace":
                displayController.unappend();
                inputValidator.removeToken();
                break;
            case "btnSimplePlusMinus":
                displayController.append("-");
                break;
            default:
                String clickedButtonText = sourceBtn.getText();
                if (inputValidator.isValid(clickedButtonText)) {
                    inputValidator.addToken(clickedButtonText);
                    displayController.append(clickedButtonText + " ");
                }
        }
    }

}
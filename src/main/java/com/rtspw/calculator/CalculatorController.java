package com.rtspw.calculator;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CalculatorController {

    @FXML
    private Label simpleCalcDisplay;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void initialize() {
        simpleCalcDisplay.setText("");
    }

    @FXML
    private void handleSimpleCalcButton(MouseEvent event) {
        Button sourceBtn = (Button)event.getSource();
        String sourceId = sourceBtn.getId();
        String currentDisplayText = simpleCalcDisplay.getText();

        switch (sourceId) {
            case "btnSimpleEquals":
                simpleCalcDisplay.setText("");
                break;
            case "btnSimpleBackspace":
                simpleCalcDisplay.setText(currentDisplayText.substring(0, currentDisplayText.length() - 1));
                break;
            case "btnSimplePlusMinus":
                simpleCalcDisplay.setText(currentDisplayText + "-");
                break;
            default:
                simpleCalcDisplay.setText(currentDisplayText + sourceBtn.getText());
        }
    }

}
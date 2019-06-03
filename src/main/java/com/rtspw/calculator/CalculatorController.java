package com.rtspw.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CalculatorController {

    private ButtonHandler simpleButtonHandler;
    private ButtonHandler scientificButtonHandler;

    @FXML
    private Label simpleCalcDisplay;

    @FXML
    private Label scientificCalcDisplay;

    @FXML
    private void initialize() {
        simpleButtonHandler = new SimpleCalcButtonHandler(
            simpleCalcDisplay,
            new BasicInputValidator(),
            new InfixEquationSolver()
        );
        scientificButtonHandler = new ScientificCalcButtonHandler(
            scientificCalcDisplay,
            new BasicInputValidator(),
            new InfixEquationSolver()
        );
    }

    @FXML
    private void handleSimpleCalcButton(MouseEvent event) {
        simpleButtonHandler.handleEvent(event);
    }

    @FXML
    private void handleScientificCalcButton(MouseEvent event) {
        scientificButtonHandler.handleEvent(event);
    }
}
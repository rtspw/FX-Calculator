package com.rtspw.calculator;

import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CalculatorController {

    private ButtonHandler simpleButtonHandler;
    private ButtonHandler scientificButtonHandler;
    private LineGraphHandler lineGraphHandler;

    @FXML
    private Label simpleCalcDisplay;
    @FXML
    private Label scientificCalcDisplay;

    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private TextField slopeInput;
    @FXML
    private TextField yInterceptInput;
    @FXML
    private TextField xMinInput;
    @FXML
    private TextField xMaxInput;

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
        lineGraphHandler = new LineGraphHandler(
            lineChart, xAxis, slopeInput, yInterceptInput, xMinInput, xMaxInput
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

    @FXML
    private void handleGraphLineButton(MouseEvent event) {
        lineGraphHandler.handleEvent(event);
    }

}
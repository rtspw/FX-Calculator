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

    @FXML
    private Label simpleCalcDisplay;
    @FXML
    private Label scientificCalcDisplay;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
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
        double slope = 1;
        double yIntercept = 0;
        double xMin = 0;
        double xMax = 50;
        try {
            slope = Double.parseDouble(slopeInput.getText());
        } catch (Exception error) {
            slopeInput.setText(Double.toString(slope));
        }
        try {
            yIntercept = Double.parseDouble(yInterceptInput.getText());
        } catch (Exception error) {
            yInterceptInput.setText(Double.toString(yIntercept));
        }
        try {
            xMin = Double.parseDouble(xMinInput.getText());
        } catch (Exception error) {
            xMinInput.setText(Double.toString(xMin));
        }
        try {
            xMax = Double.parseDouble(xMaxInput.getText());
        } catch (Exception error) {
            xMaxInput.setText(Double.toString(xMax));
        }

        if (xMin >= xMax) return;

        lineChart.getData().clear();
        xAxis.setLowerBound(xMin);
        xAxis.setUpperBound(xMax);
        xAxis.setTickUnit(calculateTickUnit(xMax - xMin));

        XYChart.Series<Number, Number> line = new XYChart.Series<>();
        line.getData().add(new XYChart.Data<>(xMin - 1, yIntercept + slope * (xMin - 1)));
        line.getData().add(new XYChart.Data<>(xMax + 1, yIntercept + slope * (xMax + 1)));
        lineChart.getData().add(line);
    }

    private double calculateTickUnit(double range) {
        if (range <= 1) return range / 10;
        if (range <= 10) return 1;
        return Math.floor(range / 10);
    }
}
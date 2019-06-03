package com.rtspw.calculator;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

class LineGraphHandler {

    private LineChart<Number, Number> lineChart;
    private NumberAxis xAxis;
    private TextField slopeInput;
    private TextField yInterceptInput;
    private TextField xMinInput;
    private TextField xMaxInput;

    LineGraphHandler(
        LineChart<Number, Number> lineChart,
        NumberAxis xAxis,
        TextField slopeInput,
        TextField yInterceptInput,
        TextField xMinInput,
        TextField xMaxInput
    ) {
        this.lineChart = lineChart;
        this.xAxis = xAxis;
        this.slopeInput = slopeInput;
        this.yInterceptInput = yInterceptInput;
        this.xMinInput = xMinInput;
        this.xMaxInput = xMaxInput;
    }

    void handleEvent(MouseEvent event) {
        LineGraphInfo fieldData = getInfoFromFields();
        if (!dataIsValid(fieldData)) return;
        eraseOldGraph();
        setAxisData(fieldData);
        graphNewLine(fieldData);
    }

    private boolean dataIsValid(LineGraphInfo fieldData) {
        return fieldData.getXMin() < fieldData.getXMax();
    }

    private void eraseOldGraph() {
        lineChart.getData().clear();
    }

    private void setAxisData(LineGraphInfo fieldData) {
        double range = fieldData.getXMax() - fieldData.getXMin();
        xAxis.setLowerBound(fieldData.getXMin());
        xAxis.setUpperBound(fieldData.getXMax());
        xAxis.setTickUnit(calculateTickUnit(range));
    }

    private void graphNewLine(LineGraphInfo fieldData) {
        XYChart.Series<Number, Number> line = new XYChart.Series<>();
        double offscreenMinX = fieldData.getXMin() - 1;
        double offscreenMaxX = fieldData.getXMax() + 1;
        double minY = solveYUsingLineFunction(offscreenMinX, fieldData.getSlope(), fieldData.getYIntercept());
        double maxY = solveYUsingLineFunction(offscreenMaxX, fieldData.getSlope(), fieldData.getYIntercept());
        line.getData().add(new XYChart.Data<>(offscreenMinX, minY));
        line.getData().add(new XYChart.Data<>(offscreenMaxX, maxY));
        lineChart.getData().add(line);
    }

    private double solveYUsingLineFunction(double x, double slope, double yIntercept) {
        return (slope * x) + yIntercept;
    }

    private LineGraphInfo getInfoFromFields() {
        LineGraphInfo fieldData = new LineGraphInfo();
        try {
            fieldData.setSlope(Double.parseDouble(slopeInput.getText()));
        } catch (Exception error) {
            slopeInput.setText(Double.toString(fieldData.getSlope()));
        }

        try {
            fieldData.setYIntercept(Double.parseDouble(yInterceptInput.getText()));
        } catch (Exception error) {
            yInterceptInput.setText(Double.toString(fieldData.getYIntercept()));
        }

        try {
            fieldData.setXMin(Double.parseDouble(xMinInput.getText()));
        } catch (Exception error) {
            xMinInput.setText(Double.toString(fieldData.getXMin()));
        }

        try {
            fieldData.setXMax(Double.parseDouble(xMaxInput.getText()));
        } catch (Exception error) {
            xMaxInput.setText(Double.toString(fieldData.getXMax()));
        }

        return fieldData;
    }

    private double calculateTickUnit(double range) {
        if (range <= 1) return range / 10;
        if (range <= 10) return 1;
        return Math.floor(range / 10);
    }
}

package com.rtspw.calculator;

class LineGraphInfo {
    private double slope = 1;
    private double yIntercept = 0;
    private double xMin = 0;
    private double xMax = 50;

    LineGraphInfo() {}

    LineGraphInfo(double slope, double yIntercept, double xMin, double xMax) {
        this.setSlope(slope);
        this.setYIntercept(yIntercept);
        this.setXMin(xMin);
        this.setXMax(xMax);
    }

    double getSlope() {
        return slope;
    }

    double getYIntercept() {
        return yIntercept;
    }

    double getXMin() {
        return xMin;
    }

    double getXMax() {
        return xMax;
    }

    void setSlope(double slope) {
        this.slope = slope;
    }

    void setYIntercept(double yIntercept) {
        this.yIntercept = yIntercept;
    }

    void setXMin(double xMin) {
        this.xMin = xMin;
    }

    void setXMax(double xMax) {
        this.xMax = xMax;
    }
}

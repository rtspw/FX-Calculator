module com.rtspw.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.rtspw.calculator to javafx.fxml;
    exports com.rtspw.calculator;
}
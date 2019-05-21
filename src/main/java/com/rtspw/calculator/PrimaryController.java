package com.rtspw.calculator;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.animation.FillTransition;
import javafx.util.Duration;

public class PrimaryController {

    @FXML
    private Button primaryButton;

    @FXML
    private Rectangle primaryButtonBackground;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void initialize() {
        String buttonBaseStyle = primaryButton.getStyle();
        primaryButton.setOnMouseEntered(event -> {
            FillTransition fillTransition = new FillTransition(Duration.seconds(0.25), primaryButtonBackground);
            fillTransition.setToValue(Color.web("#aaa"));
            fillTransition.setFromValue(Color.WHITE);
            fillTransition.play();
//            primaryButton.setStyle(buttonBaseStyle + "-fx-background-color: #aaa;");
        });
        primaryButton.setOnMouseExited(event -> {
            FillTransition fillTransition = new FillTransition(Duration.seconds(0.25), primaryButtonBackground);
            fillTransition.setFromValue(Color.web("#aaa"));
            fillTransition.setToValue(Color.WHITE);
            fillTransition.play();
            primaryButton.setStyle(buttonBaseStyle);
        });
    }
}

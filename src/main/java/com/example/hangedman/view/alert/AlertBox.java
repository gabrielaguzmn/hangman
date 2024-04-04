package com.example.hangedman.view.alert;

import com.example.hangedman.view.GameStage;
import com.example.hangedman.view.WelcomeStage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;

public class AlertBox implements IAlertBox {

    @Override
    public void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


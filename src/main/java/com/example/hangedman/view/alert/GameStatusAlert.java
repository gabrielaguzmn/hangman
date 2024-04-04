package com.example.hangedman.view.alert;

import com.example.hangedman.view.GameStage;
import com.example.hangedman.view.WelcomeStage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;

public class GameStatusAlert implements IAlertBox {

    @Override
    public void showMessage(String title, String message){
        Alert gameStatusAlert = new Alert(Alert.AlertType.INFORMATION);
        gameStatusAlert.setTitle(title);
        gameStatusAlert.setHeaderText(null);
        gameStatusAlert.setContentText(message);
        gameStatusAlert.getButtonTypes().setAll(ButtonType.OK);
        gameStatusAlert.setOnCloseRequest(eventOver -> {
            GameStage.deleteInstance();
            try {
                WelcomeStage.getInstance();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gameStatusAlert.showAndWait();
    }
}

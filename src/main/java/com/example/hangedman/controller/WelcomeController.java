package com.example.hangedman.controller;

import com.example.hangedman.model.SecretWord;
import com.example.hangedman.view.GameStage;
import com.example.hangedman.view.WelcomeStage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class WelcomeController {

    public SecretWord secretWord;
    @FXML
    private TextField textField;
    @FXML
    private void initialize() {
    }

    @FXML
    private void validateWord() {
        String word = textField.getText().trim();
        if (isValidWord(word)) {
            word = word.toLowerCase();
            SecretWord secretWord = new SecretWord(word); // Crea la palabra
            try {
                WelcomeStage.deleteInstance();
                GameController gameController = GameStage.getInstance().getGameController();
                gameController.setSecretWord(secretWord);
                gameController.setSecretWordContainer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Error", "La palabra contiene caracteres no alfabéticos, intente nuevamente.");
        }
    }

    private boolean isValidWord(String word) {
        return word.matches("[a-zA-Z]+");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
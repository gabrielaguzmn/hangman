package com.example.hangedman.controller;

import com.example.hangedman.model.SecretWord;
import com.example.hangedman.view.GameStage;
import com.example.hangedman.view.HelpStage;
import com.example.hangedman.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class GameController {

    @FXML
    private HBox wrongLettersContainer;

    @FXML
    private ImageView hangmanStatus;

    @FXML
    private TextField letterField;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private HBox secretWordContainer;
    private SecretWord secretWord;

    int j = 0;

    void setSecretWordContainer() {
        for (int i = 0; i < secretWord.getWord().length(); i++) {
            TextField wordTxt = new TextField();
            wordTxt.setEditable(false);
            secretWordContainer.getChildren().add(wordTxt);
        }
    }

    @FXML
    void OnHandleButtonValidateLetter(ActionEvent event) {
        String PATH = "/com/example/hangedman/images/hangman/";
        String letter = letterField.getText().toLowerCase();
        boolean found = false;
        if (isValidWord(letter) && letter.length()==1){
            for (int i = 0; i < secretWord.getWord().length(); i++){
                if (secretWord.getArrayWord()[i].equals(letter)){
                    resultTextArea.setText("Tu letra "+ letter + " esta en la palabra secreta");
                    TextField wordTxt = (TextField) secretWordContainer.getChildren().get(i);
                    wordTxt.setText(letter);
                    found = true;
                }
            }
            if (!found) {
                TextField wrongLetter = (TextField) wrongLettersContainer.getChildren().get(j);
                wrongLetter.setText(letter);
                Image hangman = new Image(String.valueOf(getClass().getResource(PATH  + (j+1) + ".png")));
                hangmanStatus.setImage(hangman);
                resultTextArea.setText("La letra " + letter + " no está en la palabra secreta");
                j++;
//                if (j==5){
//                    //Logica del codigo para que muestre un AlertBox y redirija a la pantalla de inicio
//
//                }
            }
        }
        else if ( !isValidWord(letter) || letter.length()!=1){
            showAlert("Error",
                    "La letra que ingresaste contiene caracteres no alfabeticos o su extension es " +
                            "mayor a la esperada. Intenta de nuevo");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void setSecretWord(SecretWord secretWord) {
        this.secretWord = secretWord;
    }
    @FXML
    void OnHandleButtonBackTo(ActionEvent event) throws IOException {
        GameStage.deleteInstance();
        WelcomeStage.getInstance();

    }

    @FXML
    void OnHandleButtonHowTo(ActionEvent event) {
        try {
            HelpStage.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onHandleButtonHelp(ActionEvent event) {

    }

    private boolean isValidWord(String word) {

        return word.matches("[a-zA-Z]+");
    }
}

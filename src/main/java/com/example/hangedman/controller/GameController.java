package com.example.hangedman.controller;

import com.example.hangedman.model.SecretWord;
import com.example.hangedman.view.GameStage;
import com.example.hangedman.view.HelpStage;
import com.example.hangedman.view.WelcomeStage;
import com.example.hangedman.view.alert.AlertBox;
import com.example.hangedman.view.alert.GameStatusAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import com.example.hangedman.view.alert.AlertBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Arrays;

public class GameController {

    @FXML
    private HBox wrongLettersContainer;

    @FXML
    private ImageView hangmanStatus;

    @FXML
    private TextField letterField;

    @FXML
    private TextField outputTextArea;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private HBox secretWordContainer;
    private SecretWord secretWord;

    int j = 0;

    String[] word;

    public void WinMethod(){
        if (Arrays.equals(word, secretWord.getArrayWord())){
            GameStatusAlert gameStatusAlert = new GameStatusAlert();
            gameStatusAlert.showMessage("Has ganado", "Enhorabuena! Has adivinado la palabra");
        }

    }


    void setSecretWordContainer() {
        for (int i = 0; i < secretWord.getWord().length(); i++) {
            TextField wordTxt = new TextField();
            wordTxt.setEditable(false);
            secretWordContainer.getChildren().add(wordTxt);
        }
    }

    @FXML
    void OnHandleButtonValidateLetter(ActionEvent event) {
        boolean secretWordValidation = false;
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
                    word[i]= letter;
                }
            }
            if (!found) {
                TextField wrongLetter = (TextField) wrongLettersContainer.getChildren().get(j);
                wrongLetter.setText(letter);
                Image hangman = new Image(String.valueOf(getClass().getResource(PATH  + (j+1) + ".png")));
                hangmanStatus.setImage(hangman);
                resultTextArea.setText("La letra " + letter + " no está en la palabra secreta");
                j++;
                if (j==6){
                    GameStatusAlert gameStatusAlert = new GameStatusAlert();
                    gameStatusAlert.showMessage("Has perdido", "Lamentablemente no has adivinado la palabra. "+
                    "Tu palabra era "+secretWord.getWord());
                }
            }
            WinMethod();
        }
        else if ( !isValidWord(letter) || letter.length()!=1){
            AlertBox alertBox = new AlertBox();
            alertBox.showMessage("Error",
                    "La letra que ingresaste contiene caracteres no alfabeticos o su extension es " +
                            "mayor a la esperada. Intenta de nuevo");
        }
    }

    public void setSecretWord(SecretWord secretWord) {
        this.secretWord = secretWord;
        word = new String[secretWord.getArrayWord().length];

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
        int tryRemainI;


        String tryRemain= outputTextArea.getText();
        tryRemainI = Integer.parseInt(tryRemain);
        if (tryRemainI > 0){
            tryRemainI = tryRemainI - 1;

            tryRemain = Integer.toString(tryRemainI);
            outputTextArea.setText(tryRemain);





            int clue = (int)(Math.random() * secretWord.getWord().length()) ;
            while(word[clue] == secretWord.getArrayWord()[clue]){
                 clue = (int)(Math.random() * secretWord.getWord().length()) ;
            }

            if (word[clue] == null || word[clue].isEmpty()) {
                String letterToFind = secretWord.getArrayWord()[clue];


                for (int i = 0; i < secretWord.getWord().length(); i++) {
                    if (secretWord.getArrayWord()[i].equals(letterToFind)) {
                        word[i] = letterToFind;
                        TextField wordTxt = (TextField) secretWordContainer.getChildren().get(i);
                        wordTxt.setText(letterToFind);
                    }
                }
            }



            WinMethod();
        }

        else {
            AlertBox alertBox = new AlertBox();
            alertBox.showMessage("Error",  "has gastado todas las ayudas disponibles");

        }
    }

    private boolean isValidWord(String word) {
        return word.matches("[a-zA-Z]+");
    }
}

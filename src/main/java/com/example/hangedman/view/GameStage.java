package com.example.hangedman.view;

import com.example.hangedman.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import com.example.hangedman.controller.GameController;

import java.io.IOException;

public class GameStage extends Stage{

    private GameController gameController;

    public GameStage() throws IOException{
        FXMLLoader loader = new FXMLLoader
                (getClass().getResource("/com/example/hangedman/game-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        Scene scene = new Scene(root);
        setTitle("Hangman game");
        setResizable(false);
        setScene(scene);
        show();
    }

    public static GameStage getInstance() throws IOException{
        return GameStageHolder.INSTANCE = new GameStage();
    }


    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }


    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    public GameController getGameController(){
        return gameController;
    }

}
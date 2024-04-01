package com.example.hangedman.view;
import com.example.hangedman.controller.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeStage extends Stage {

    private WelcomeController welcomeController;
    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/hangedman/welcome-view.fxml"));
        Parent root = loader.load();
        welcomeController = loader.getController();
        Scene scene = new Scene(root);
        setTitle("Hangman");
        setResizable(false);
        setScene(scene);
        show();
    }
    public static WelcomeStage getInstance() throws IOException{
        return WelcomeStage.WelcomeStageHolder.INSTANCE = new WelcomeStage();
    }

    public static void deleteInstance() {
        WelcomeStage.WelcomeStageHolder.INSTANCE.close();
        WelcomeStage.WelcomeStageHolder.INSTANCE = null;
    }

    public WelcomeController getWelcomeController() {
        return welcomeController;
    }

    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }
}


package com.example.hangedman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.example.hangedman.view.HelpStage;

public class HelpController {

    @FXML
    void OnHandleButtonClose(ActionEvent event) {
        HelpStage.deleteInstance();

    }

}

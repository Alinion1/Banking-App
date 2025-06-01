package com.example.aplicatiebancara1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicatieController  {
    @FXML
    private Button button;
    @FXML
    protected void onLogInCLick(){
        Stage stage = (Stage) button.getScene().getWindow();
        SceneSwitcher.switchToScene(stage,"Menu.fxml",500,500);
    }
}
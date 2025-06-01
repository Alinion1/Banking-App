package com.example.aplicatiebancara1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Istoric {
    @FXML
    private Button butonInapoi;

    @FXML
    private void onInapoiClick(){
        Stage stage = (Stage) butonInapoi.getScene().getWindow();
        SceneSwitcher.switchToScene(stage,"Menu.fxml",500,500);
    }
}

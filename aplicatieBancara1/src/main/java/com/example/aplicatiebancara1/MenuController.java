package com.example.aplicatiebancara1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.aplicatiebancara1.ShowHide.hideVBox;
import static com.example.aplicatiebancara1.ShowHide.showVBox;

public class MenuController {

    @FXML
    private Button logOut;
    @FXML
    private Button menu;
    @FXML
    private VBox vBox;
    @FXML
    private Pane interfata;
    @FXML
    private Pane interfata1;

    private String actiune;

    @FXML
    private void onLogOutClick(){
        Stage stage = (Stage) logOut.getScene().getWindow();
        SceneSwitcher.switchToScene(stage,"interfataPrimara.fxml",500,500);
    }

    @FXML
    private Button butonRetrage;

    @FXML
    private void onIstoricClick(){
        Stage stage = (Stage) butonRetrage.getScene().getWindow();
        SceneSwitcher.switchToScene(stage,"Istoric.fxml",500,500);
    }




    public void initialize() {
        // Setează VBox-ul să fie inițial în afacerea dreaptă a ecranului
        vBox.setTranslateX(386); // Ajustează acest număr în funcție de lățimea VBox-ului
        logOut.setTranslateX(386);
        interfata.setTranslateY(516);
        interfata1.setTranslateY(516);
        vBox.setVisible(false);  // Îl face invizibil la început
        logOut.setVisible(false);
        interfata.setVisible(false);
        interfata1.setVisible(false);
    }
    @FXML
    public void toggleVBox() {
        if (vBox.isVisible()) {
            hideVBox(vBox,logOut);
        } else {
            if (interfata.isVisible())
                ShowHide.showPane(interfata);
            if (interfata1.isVisible())
                ShowHide.showPane(interfata1);
            showVBox(vBox,logOut);

        }
    }

    @FXML
    private void onTrimiteClick(ActionEvent event){

            if(vBox.isVisible())//daca meniul este deschis
                hideVBox(vBox,logOut);


            if(!interfata.isVisible())
                ShowHide.showPane(interfata);

            if(interfata.isVisible()&& !Objects.equals(actiune, ((Button) event.getSource()).getText())){
                interfata.setTranslateY(516);
                ShowHide.showPane(interfata);
            }//daca apasam alt buton sa se realizeze animatia de deschidere din nou



        actiune=((Button) event.getSource()).getText();//salvam textul butonului(pt actiunea bazei de date)
        System.out.println(actiune);
    }

    @FXML
    private void onRetragereClick(ActionEvent event){

        if(vBox.isVisible())//daca meniul este deschis
            hideVBox(vBox,logOut);


        if(!interfata1.isVisible())
            ShowHide.showPane(interfata1);

        if(interfata1.isVisible()&& !Objects.equals(actiune, ((Button) event.getSource()).getText())){
            interfata1.setTranslateY(516);
            ShowHide.showPane(interfata1);
        }//daca apasam alt buton sa se realizeze animatia de deschidere din nou



        actiune=((Button) event.getSource()).getText();//salvam textul butonului(pt actiunea bazei de date)
        System.out.println(actiune);
    }

    @FXML
    private void onTrimiteCLose(){
        ShowHide.hidePane(interfata);
    }

    @FXML
    private void onRetragereCLose(){
        ShowHide.hidePane(interfata1);
    }




}




package com.example.aplicatiebancara1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class AplicatieController {
    @FXML private Button button;
    @FXML private Button button1;
    @FXML private Button createAccountButton;
    @FXML private Button CLose;
    @FXML private Pane panel_aut;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordCreate;
    @FXML private TextField usernameCreate;
    @FXML private TextField nume;
    @FXML private TextField prenume;
    @FXML private TextField pin;

    private String actiune;

    public void initialize() {
        panel_aut.setTranslateY(516);
        panel_aut.setVisible(false);
    }

    @FXML
    protected void onLogInCLick() {
        String idUtilizator = username.getText();
        String parola = password.getText();

        if (idUtilizator.isEmpty() || parola.isEmpty()) {
            showError("Te rugăm să completezi ambele câmpuri!");
            return;
        }

        Utilizator utilizator = UtilizatorDAO.login(idUtilizator, parola);
        if (utilizator != null) {
            HelloApplication.setUtilizatorAutentificat(utilizator);
            Stage stage = (Stage) button.getScene().getWindow();
            SceneSwitcher.switchToScene(stage, "Menu.fxml", 500, 500);
        } else {
            showError("Username sau parolă incorecte!");
        }
    }

    @FXML
    protected void onCreateAccountClick() {
        String idUtilizator = usernameCreate.getText();
        String parola = passwordCreate.getText();
        String numeText = nume.getText();
        String prenumeText = prenume.getText();
        String pinText = pin.getText();

        if (idUtilizator.isEmpty() || parola.isEmpty() || numeText.isEmpty() || prenumeText.isEmpty() || pinText.isEmpty()) {
            showError("Te rugăm să completezi toate câmpurile!");
            return;
        }

        if (!pinText.matches("\\d{4}")) {
            showError("PIN-ul trebuie să fie un număr de exact 4 cifre!");
            return;
        }

        if (UtilizatorDAO.existaUtilizator(idUtilizator)) {
            showError("Utilizatorul există deja!");
            return;
        }

        Utilizator utilizator = new Utilizator(parola, numeText, prenumeText, 0.0, pinText);
        utilizator.setIdUtilizator(idUtilizator);

        if (UtilizatorDAO.adaugaUtilizator(utilizator)) {
            showInfo("Cont creat cu succes! Te poți autentifica.");
            usernameCreate.clear();
            passwordCreate.clear();
            nume.clear();
            prenume.clear();
            pin.clear();
            ShowHide.hidePane(panel_aut);
        } else {
            showError("Eroare la crearea contului. Încearcă din nou.");
        }
    }

    @FXML
    protected void onAutentificareCLick(ActionEvent event) {
        if (!panel_aut.isVisible()) {
            ShowHide.showPane(panel_aut);
        }

        if (panel_aut.isVisible() && !Objects.equals(actiune, ((Button) event.getSource()).getText())) {
            panel_aut.setTranslateY(516);
            ShowHide.showPane(panel_aut);
        }

        actiune = ((Button) event.getSource()).getText();
    }

    @FXML
    private void onClickClose() {
        ShowHide.hidePane(panel_aut);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eroare");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informație");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

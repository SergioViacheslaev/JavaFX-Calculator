package org.home.calculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.home.calculator.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField primaryLoginField;

    @FXML
    private Button primaryEnterButton;

    @FXML
    private PasswordField primaryPasswordField;

    @FXML
    private Button primaryRegisterButton;

    @FXML
    void initialize() {

        primaryEnterButton.setOnAction(actionEvent -> {
            String userName = primaryLoginField.getText();
            String userPassword = primaryPasswordField.getText();
            try {
                if (authorizeUser(userName, userPassword)) {
                    switchToSecondary();
                } else {
                    showAlertWithoutHeaderText();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    private boolean authorizeUser(String userName, String userPassword) {
        boolean isAuthorized = false;
        if (!userName.equals("") && !userPassword.equals("")) {
            if (userName.equals("mops") && userPassword.equals("mops")) {
                isAuthorized = true;
            }
        }

        return isAuthorized;
    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Неверный логин или пароль !");
        alert.showAndWait();
    }


    public void registerButtonAction(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        System.out.println("Регистрация");
    }
}



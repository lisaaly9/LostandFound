package org.oop.lostfound.controller;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;


public class FormLoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Hyperlink registerHyperlink;
    @FXML
    private Button loginButton;
    
    @FXML
    private void usernameTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void passwordFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registerHyperlinkOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormRegister.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }
    @FXML
    private void loginButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormRegister.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }




}
package org.oop.lostfound.controller;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.Connection;
import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.RegisterDAO;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;


public class FormRegisterController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField retypePasswordField;
    @FXML
    private Hyperlink loginHyperlink;
    @FXML
    private Button registerButton;
    
    @FXML
    private void usernameTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void phoneTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) phoneTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void emailTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) emailTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void passwordFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void retypePasswordFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) retypePasswordField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void loginHyperlinkOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLogin.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }
    @FXML
    private void registerButtonOnAction(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String phone_number = phoneTextField.getText();
        String email = emailTextField.getText();
        String user_password = passwordField.getText();
        String retype_password = retypePasswordField.getText();
        if (!user_password.equals(retype_password)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("PESAN ERROR");
            alert.setHeaderText(null);
            alert.setContentText("PASSWORD TIDAK SAMA");
            alert.showAndWait();
            return;
        }
        RegisterDAO registerDAO = new RegisterDAO(Connector.getConnection());
        boolean success = registerDAO.registerUser(username, phone_number, email, user_password);

        if (success) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("PESAN SUKSES");
            alert.setHeaderText(null);
            alert.setContentText("REGISTRASI SUKSES");
            alert.showAndWait();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLogin.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
            
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("PESAN ERROR");
            alert.setHeaderText(null);
            alert.setContentText("PENDAFTARAN GAGAL");
            alert.showAndWait();
            return;
        }
    }




}

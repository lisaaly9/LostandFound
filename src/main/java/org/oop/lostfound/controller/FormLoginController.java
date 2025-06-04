package org.oop.lostfound.controller;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.LoginDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        String username = usernameTextField.getText();
        String user_password = passwordField.getText();

        if (username.isEmpty() || user_password.isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("PESAN PERINGATAN");
            alert.setHeaderText(null);
            alert.setContentText("USERNAME ATAU PASSWORD TIDAK BOLEH KOSONG!");
            alert.showAndWait();
            return;
        }

        LoginDAO loginDAO = new LoginDAO(Connector.getConnection());
        boolean success = loginDAO.checkLogin(username, user_password);

        if (success) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("PESAN SUKSES");
            alert.setHeaderText(null);
            alert.setContentText("LOGIN BERHASIL SILAHKAN MASUK ^^");
            alert.showAndWait();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
            
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("PESAN ERROR");
            alert.setHeaderText(null);
            alert.setContentText("USERNAME ATAU PASSWORD SALAH!");
            alert.showAndWait();
            return;
        }
    }




}
package org.oop.lostfound.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.RegisterAdminDAO;

import java.io.IOException;

public class FormRegisterAdminController
{
    @FXML private TextField usernameTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField retypePasswordField;

    @FXML
    private void usernameTextFieldOnAction(ActionEvent event) {
        phoneTextField.requestFocus();
    }

    @FXML
    private void phoneTextFieldOnAction(ActionEvent event) {
        emailTextField.requestFocus();
    }

    @FXML
    private void emailTextFieldOnAction(ActionEvent event) {
        passwordField.requestFocus();
    }

    @FXML
    private void passwordFieldOnAction(ActionEvent event) {
        retypePasswordField.requestFocus();
    }

    @FXML
    private void retypePasswordFieldOnAction(ActionEvent event) throws IOException {
        registerAdminButtonOnAction(event);
    }

    @FXML
    private void registerAdminButtonOnAction(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        String password = passwordField.getText();
        String retypePassword = retypePasswordField.getText();

        if (username.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || retypePassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Semua field harus diisi!");
            return;
        }

        if (!phone.matches("\\d+")) {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Phone hanya boleh angka!");
            return;
        }

        if (!password.equals(retypePassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Password tidak sama!");
            return;
        }

        RegisterAdminDAO dao = new RegisterAdminDAO(Connector.getConnection());
        boolean success = dao.registerAdmin(username, phone, email, password);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Registrasi admin berhasil!");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLogin.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Registrasi admin gagal!");
        }
    }

    @FXML
    private void loginHyperlinkOnAction(javafx.scene.input.MouseEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormRegister.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

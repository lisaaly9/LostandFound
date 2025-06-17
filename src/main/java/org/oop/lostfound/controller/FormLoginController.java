package org.oop.lostfound.controller;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
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
import org.oop.lostfound.config.Session;
import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.LoginDAO;
import org.oop.lostfound.model.User;
import org.oop.lostfound.dao.LoginDAO;
import org.oop.lostfound.dao.LoginAdminDAO;
import org.oop.lostfound.model.User;
import org.oop.lostfound.model.Admin;


public class FormLoginController
{
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Hyperlink registerHyperlink;
    @FXML private Button loginButton;
    
    // Logout untuk setiap launch
    @FXML
    public void initialize()
    {
        Session.setId(0);
        Session.setUsername(null);
    }

    @FXML
    private void usernameTextFieldOnAction(ActionEvent event)
    {
        passwordField.requestFocus();
    }

    @FXML
    private void passwordFieldOnAction(ActionEvent event) throws IOException
    {
        loginButtonOnAction(event);
    }

    @FXML
    private void registerHyperlinkOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormRegister.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    // Login
    @FXML
    private void loginButtonOnAction(ActionEvent event) throws IOException {
        String usernameOrEmail = usernameTextField.getText();
        String password = passwordField.getText();

        LoginDAO loginDAO = new LoginDAO(Connector.getConnection());
        User user = loginDAO.checkLogin(usernameOrEmail, password);

        if (user != null) {
            Session.setId(user.getId());
            Session.setUsername(user.getUsername());
            Session.setRole("user"); // Tambahkan baris ini

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Selamat datang, " + user.getUsername() + "!");
            alert.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
            return;
        } else {
            LoginAdminDAO adminDAO = new LoginAdminDAO(Connector.getConnection());
            Admin admin = adminDAO.checkLogin(usernameOrEmail, password);

            if (admin != null) {
                Session.setId(admin.getId());
                Session.setUsername(admin.getUsername());
                Session.setRole("admin");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login Admin Berhasil");
                alert.setHeaderText(null);
                alert.setContentText("Selamat datang Admin, " + admin.getUsername() + "!");
                alert.showAndWait();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
                Parent parent = fxmlLoader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();
                return;
            } else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Gagal");
                alert.setHeaderText(null);
                alert.setContentText("Username/email atau password salah!");
                alert.showAndWait();
            }
        }
    }
}
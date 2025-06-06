package org.oop.lostfound.controller;

import java.io.IOException;
import java.time.LocalDate;
import org.oop.lostfound.enums.Category;
import org.oop.lostfound.enums.ItemType;
import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.LostItemDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormLostItemController {
    @FXML
    private Button lostFoundButton;
    @FXML
    private Button lostItemButton;
    @FXML
    private Button foundItemButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button claimButton;
    @FXML
    private Button logOutButton; 
    @FXML
    private TextField itemNameTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private DatePicker dateLostDatePicker;
    @FXML
    private ComboBox<Category> categoryComboBox;
    @FXML
    private ComboBox<ItemType> typeComboBox;
    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        categoryComboBox.getItems().setAll(Category.values());
        typeComboBox.getItems().setAll(ItemType.values());
    }

    @FXML
    private void lostFoundButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void lostItemButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLostItem.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void foundItemButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormFoundItem.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void reportButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormReportUser.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void claimButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormClaim.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void logOutButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLogin.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void itemNameTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) itemNameTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void descriptionTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) descriptionTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void locationTextFieldOnAction(ActionEvent event) {
        Stage stage = (Stage) locationTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void dateLostDatePickerOnAction(ActionEvent event) {
        LocalDate selectDate = dateLostDatePicker.getValue();
        System.out.println("Tanggal yang dipilih: " + selectDate);
    }

    @FXML
    private void categoryComboBoxOnAction(ActionEvent event) {
        Category selectedCategory = categoryComboBox.getValue();
        if (selectedCategory != null) {
            System.out.println("Kategori dipilih: " + selectedCategory.name());
            
        }
    }

    @FXML
    private void typeComboBoxOnAction(ActionEvent event) {
    ItemType selectedType = typeComboBox.getValue();
    if (selectedType != null) {
        System.out.println("Tipe item dipilih: " + selectedType.name());
    }
}
    
    @FXML
    private void submitButtonOnAction(ActionEvent event) throws IOException {
        String itemName = itemNameTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        LocalDate dateLost = dateLostDatePicker.getValue();
        Category category = categoryComboBox.getValue();
        ItemType type = typeComboBox.getValue();

        if (itemName.isEmpty() || description.isEmpty() || location.isEmpty() || dateLost == null || category == null || type == null )  {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("PESAN PERINGATAN");
            alert.setHeaderText(null);
            alert.setContentText("TIDAK BOLEH ADA YANG KOSONG WAJIB DIISI SEMUA!");
            alert.showAndWait();
            return;
        }
        LostItemDAO lostitemDAO = new LostItemDAO(Connector.getConnection());
        boolean success = lostitemDAO.insertLostItem(itemName, description, location, dateLost, category, type);

        if (success) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("BERHASIL");
            alert.setHeaderText(null);
            alert.setContentText("DATA BARANG HILANG BERHASIL DISIMPAN!");
            alert.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.show();
        } else { 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("GAGAL");
            alert.setHeaderText(null);
            alert.setContentText("GAGAL MENYIMPAN DATA BARANG HILANG");
            alert.showAndWait();
        }

        
    }
}
package org.oop.lostfound.controller;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.util.ResourceBundle;

import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.LostItemDAO;
import org.oop.lostfound.dao.FoundItemDAO;
// import org.oop.lostfound.dao.ClaimDAO;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

public class FormMenuUtamaController implements javafx.fxml.Initializable {
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
    
    // Label untuk setiap counter di dashboard
    @FXML
    private Label totalItemsCountLabel;
    @FXML
    private Label lostItemsCountLabel;
    @FXML
    private Label foundItemsCountLabel;
    @FXML
    private Label totalClaimsCountLabel;
    @FXML
    private VBox itemListContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateDashboardData();
        loadItemList();
    }

    /**
     * Membuat VBox sederhana untuk menampilkan nama barang dan gambar (jika ada URL gambar).
     */
    private VBox createItemVBox(String namaBarang, String imageUrl) {
        VBox box = new VBox();
        Label nameLabel = new Label(namaBarang);
        box.getChildren().add(nameLabel);
        // Jika ingin menampilkan gambar, tambahkan kode berikut:
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(new javafx.scene.image.Image(imageUrl));
                imageView.setFitWidth(100);
                imageView.setPreserveRatio(true);
                box.getChildren().add(imageView);
            } catch (Exception e) {
                // Jika gagal load gambar, abaikan saja
            }
        }
        return box;
    }
    
    private void loadItemList() {
        try {
            Connection connection = Connector.getConnection();
            LostItemDAO lostItemDAO = new LostItemDAO(connection);
            FoundItemDAO foundItemDAO = new FoundItemDAO(connection);

            itemListContainer.getChildren().clear(); // Clear existing

            // Ambil semua item dari DB
            java.util.List<?> lostItems = lostItemDAO.getAllLostItems();  // Pastikan method ini mengembalikan List
            java.util.List<?> foundItems = foundItemDAO.getAllFoundItems(); // Pastikan method ini mengembalikan List

            // Gabungkan dan tampilkan semua item
            for (var item : lostItems) {
                // Cast to the correct type, e.g., LostItem
                org.oop.lostfound.model.LostItem lostItem = (org.oop.lostfound.model.LostItem) item;
                itemListContainer.getChildren().add(createItemVBox(lostItem.getNamaBarang(), lostItem.getImageUrl()));
            }
            
            for (var item : foundItems) {
                // Cast to the correct type, e.g., FoundItem
                org.oop.lostfound.model.FoundItem foundItem = (org.oop.lostfound.model.FoundItem) item;
                itemListContainer.getChildren().add(createItemVBox(foundItem.getNamaBarang(), foundItem.getImageUrl()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateDashboardData(){
        try {
            Connection connection = Connector.getConnection();
            LostItemDAO lostItemDAO = new LostItemDAO(connection);
            FoundItemDAO foundItemDAO = new FoundItemDAO(connection);
            
            // Ambil data dari database
            int jumlahLostItems = lostItemDAO.getJumlahLostItems();
            int jumlahFoundItems = foundItemDAO.getJumlahFoundItems(); 
            int totalClaims = 0; // Ganti dengan method yang sesuai jika ClaimDAO sudah ada
            int totalItems = jumlahLostItems + jumlahFoundItems;

            // Update masing-masing label dengan angka saja
            totalItemsCountLabel.setText(String.valueOf(totalItems));
            lostItemsCountLabel.setText(String.valueOf(jumlahLostItems));
            foundItemsCountLabel.setText(String.valueOf(jumlahFoundItems));
            totalClaimsCountLabel.setText(String.valueOf(totalClaims));
            
        } catch (Exception e) {
            e.printStackTrace();
            // Set nilai default jika terjadi error
            totalItemsCountLabel.setText("0");
            lostItemsCountLabel.setText("Error");
            foundItemsCountLabel.setText("0");
            totalClaimsCountLabel.setText("0");
        }
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
}
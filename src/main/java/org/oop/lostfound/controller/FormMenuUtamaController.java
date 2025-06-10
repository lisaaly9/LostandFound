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
import javafx.scene.layout.FlowPane;

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
    private FlowPane itemListFlowPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (itemListFlowPane != null) {
            itemListFlowPane.setHgap(10); 
            itemListFlowPane.setVgap(10); 
            itemListFlowPane.setPrefWrapLength(800); 
        }
        
        updateDashboardData();
        loadItemList();
    }

    private VBox createLostItemVBox(org.oop.lostfound.model.LostItem lostItem) {
        VBox box = new VBox();
        box.setSpacing(5);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(140); // Set fixed width
        box.setMaxWidth(140);
        box.setMinWidth(140);
        
        // Style untuk box
        box.setStyle("-fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 8; -fx-background-color: #f9f9f9; -fx-background-radius: 8;");
        
        // Label nama barang
        Label nameLabel = new Label(lostItem.getNamaBarang());
        nameLabel.setMaxWidth(120);
        nameLabel.setWrapText(true);
        nameLabel.setStyle("-fx-font-size: 12px; -fx-text-alignment: center;");
        

        if (lostItem.getImageUrl() != null && !lostItem.getImageUrl().isEmpty()) {
            try {
                ImageView imageView = new ImageView();
                

                Image image = new Image(lostItem.getImageUrl(), true);
                imageView.setImage(image);
                
                // Set properties untuk ImageView
                imageView.setFitWidth(120);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 4;");
                
                // Event handler untuk klik gambar
                imageView.setOnMouseClicked(e -> {
                    org.oop.lostfound.controller.FormDetailLostItemController.showDetail(lostItem);
                });
                
                box.getChildren().addAll(imageView, nameLabel);
                
            } catch (Exception e) {
                System.err.println("Error loading image for lost item: " + e.getMessage());
                // Jika gagal load gambar, tampilkan placeholder
                Label placeholderLabel = new Label("No Image");
                placeholderLabel.setPrefSize(120, 100);
                placeholderLabel.setStyle("-fx-background-color: #eee; -fx-border-color: #ccc; -fx-alignment: center;");
                box.getChildren().addAll(placeholderLabel, nameLabel);
            }
        } else {
            // Jika tidak ada URL gambar, tampilkan placeholder
            Label placeholderLabel = new Label("No Image");
            placeholderLabel.setPrefSize(120, 100);
            placeholderLabel.setStyle("-fx-background-color: #eee; -fx-border-color: #ccc; -fx-alignment: center;");
            box.getChildren().addAll(placeholderLabel, nameLabel);
        }
        
        return box;
    }

    /**
     * Membuat VBox untuk FoundItem dengan gambar yang dapat diklik
     */
    private VBox createFoundItemVBox(org.oop.lostfound.model.FoundItem foundItem) {
        VBox box = new VBox();
        box.setSpacing(5);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(140); // Set fixed width
        box.setMaxWidth(140);
        box.setMinWidth(140);
        
        // Style untuk box
        box.setStyle("-fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 8; -fx-background-color: #f0f8ff; -fx-background-radius: 8;");
        
        // Label nama barang
        Label nameLabel = new Label(foundItem.getNamaBarang());
        nameLabel.setMaxWidth(120);
        nameLabel.setWrapText(true);
        nameLabel.setStyle("-fx-font-size: 12px; -fx-text-alignment: center;");
        
        // Tambahkan gambar jika ada
        if (foundItem.getImageUrl() != null && !foundItem.getImageUrl().isEmpty()) {
            try {
                ImageView imageView = new ImageView();
                
                // Coba load gambar
                Image image = new Image(foundItem.getImageUrl(), true); // background loading
                imageView.setImage(image);
                
                // Set properties untuk ImageView
                imageView.setFitWidth(120);
                imageView.setFitHeight(100);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setStyle("-fx-cursor: hand; -fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 4;");
                
                // Event handler untuk klik gambar
                imageView.setOnMouseClicked(e -> {
                    try {
                        org.oop.lostfound.controller.FormDetailFoundItemController.showDetail(foundItem);
                    } catch (Exception ex) {
                        System.err.println("Error opening detail form: " + ex.getMessage());
                    }
                });
                
                box.getChildren().addAll(imageView, nameLabel);
                
            } catch (Exception e) {
                System.err.println("Error loading image for found item: " + e.getMessage());
                // Jika gagal load gambar, tampilkan placeholder
                Label placeholderLabel = new Label("No Image");
                placeholderLabel.setPrefSize(120, 100);
                placeholderLabel.setStyle("-fx-background-color: #eee; -fx-border-color: #ccc; -fx-alignment: center; -fx-cursor: hand;");
                placeholderLabel.setOnMouseClicked(event -> {
                    try {
                        org.oop.lostfound.controller.FormDetailFoundItemController.showDetail(foundItem);
                    } catch (Exception ex) {
                        System.err.println("Error opening detail form: " + ex.getMessage());
                    }
                });
                box.getChildren().addAll(placeholderLabel, nameLabel);
            }
        } else {
            // Jika tidak ada URL gambar, tampilkan placeholder
            Label placeholderLabel = new Label("No Image");
            placeholderLabel.setPrefSize(120, 100);
            placeholderLabel.setStyle("-fx-background-color: #eee; -fx-border-color: #ccc; -fx-alignment: center; -fx-cursor: hand;");
            placeholderLabel.setOnMouseClicked(e -> {
                try {
                    org.oop.lostfound.controller.FormDetailFoundItemController.showDetail(foundItem);
                } catch (Exception ex) {
                    System.err.println("Error opening detail form: " + ex.getMessage());
                }
            });
            box.getChildren().addAll(placeholderLabel, nameLabel);
        }
        
        return box;
    }


    private void loadItemList() {
        try {
            Connection connection = Connector.getConnection();
            LostItemDAO lostItemDAO = new LostItemDAO(connection);
            FoundItemDAO foundItemDAO = new FoundItemDAO();

            // Clear existing items
            itemListFlowPane.getChildren().clear();

            // Ambil semua item dari DB
            java.util.List<?> lostItems = lostItemDAO.getAllLostItems();
            java.util.List<?> foundItems = foundItemDAO.getAllFoundItems();

            // Tampilkan LostItem
            for (var item : lostItems) {
                org.oop.lostfound.model.LostItem lostItem = (org.oop.lostfound.model.LostItem) item;
                itemListFlowPane.getChildren().add(createLostItemVBox(lostItem));
            }
            
            // Tampilkan FoundItem
            for (var item : foundItems) {
                org.oop.lostfound.model.FoundItem foundItem = (org.oop.lostfound.model.FoundItem) item;
                itemListFlowPane.getChildren().add(createFoundItemVBox(foundItem));
            }
            
            System.out.println("Loaded " + lostItems.size() + " lost items and " + foundItems.size() + " found items");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading item list: " + e.getMessage());
        }
    }

    @FXML
    public void updateDashboardData(){
        try {
            Connection connection = Connector.getConnection();
            LostItemDAO lostItemDAO = new LostItemDAO(connection);
            FoundItemDAO foundItemDAO = new FoundItemDAO();
            
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
            lostItemsCountLabel.setText("0");
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
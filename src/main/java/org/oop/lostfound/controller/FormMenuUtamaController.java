package org.oop.lostfound.controller;

import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.util.ResourceBundle;

import org.oop.lostfound.dao.ClaimDAO;
import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.LostItemDAO;
import org.oop.lostfound.dao.FoundItemDAO;
//import org.oop.lostfound.dao.ClaimItemDAO;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

import org.oop.lostfound.config.Session;

public class FormMenuUtamaController implements javafx.fxml.Initializable
{
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
    private Button shrinkImagesButton;


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
    @FXML
    private Label adminLabel;

    private double imageWidth = 180;
    private double imageHeight = 90;
    private boolean isShrink = false;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if (adminLabel != null)
        {
            adminLabel.setVisible("admin".equalsIgnoreCase(Session.getRole()));
        }

        if (itemListFlowPane != null)
        {
            itemListFlowPane.setHgap(10);
            itemListFlowPane.setVgap(10);
            itemListFlowPane.setPrefWrapLength(800);
        }

        updateDashboardData();
        loadItemList();
    }

    private VBox createLostItemVBox(org.oop.lostfound.model.LostItem lostItem)
    {
        VBox box = new VBox();
        box.setSpacing(5);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPrefWidth(250);
        box.setStyle("-fx-padding: 14; -fx-background-radius: 15; -fx-background-color: #fff; -fx-border-radius: 15; -fx-border-color: #eee;");

        HBox titleRow = new HBox();
        titleRow.setAlignment(Pos.TOP_LEFT);
        titleRow.setSpacing(10);

        Label nameLabel = new Label(lostItem.getName());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #222;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label badge = new Label("Lost");
        badge.setStyle("-fx-background-color: #ffcccc; -fx-text-fill: #e74c3c; -fx-padding: 3 16 3 16; -fx-background-radius: 10; -fx-font-size: 14px; -fx-font-weight: bold;");

        titleRow.getChildren().addAll(nameLabel, spacer, badge);

        Node imageNode;
        if (lostItem.getImageUrl() != null && !lostItem.getImageUrl().isEmpty())
        {
            try
            {
                ImageView imageView = new ImageView(new Image(lostItem.getImageUrl(), true));
                imageView.setFitWidth(imageWidth);
                imageView.setFitHeight(imageHeight);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setStyle("-fx-background-color: #f5f5f5; -fx-border-radius: 6; -fx-cursor: hand;");
                imageView.setOnMouseClicked(e ->
                {
                    if ("admin".equalsIgnoreCase(Session.getRole()))
                    {
                        try
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormDetailLostItemAdmin.fxml"));
                            Parent parent = loader.load();
                            FormDetailLostItemAdminController controller = loader.getController();
                            controller.setLostItem(lostItem);
                            controller.setParentController(this);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.setTitle("Detail Lost Item (Admin)");
                            stage.show();
                        } catch (Exception ex) { ex.printStackTrace(); }
                    } else
                    {
                        org.oop.lostfound.controller.FormDetailLostItemController.showDetail(lostItem);
                    }
                });
                imageNode = imageView;
            } catch (Exception e)
            {
                Label placeholder = new Label("No Image");
                placeholder.setPrefSize(imageWidth, imageHeight);
                placeholder.setAlignment(Pos.CENTER);
                placeholder.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #aaa; -fx-cursor: hand;");
                placeholder.setOnMouseClicked(ev ->
                {
                    if ("admin".equalsIgnoreCase(Session.getRole()))
                    {
                        try
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormDetailLostItemAdmin.fxml"));
                            Parent parent = loader.load();
                            org.oop.lostfound.controller.FormDetailLostItemAdminController controller = loader.getController();
                            controller.setLostItem(lostItem);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.setTitle("Detail Lost Item (Admin)");
                            stage.show();
                        } catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    } else
                    {
                        org.oop.lostfound.controller.FormDetailLostItemController.showDetail(lostItem);
                    }
                });
                imageNode = placeholder;
            }
        } else
        {
            Label placeholder = new Label("No Image");
            placeholder.setPrefSize(imageWidth, imageHeight);
            placeholder.setAlignment(Pos.CENTER);
            placeholder.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #aaa; -fx-cursor: hand;");
            placeholder.setOnMouseClicked(e -> {
                if ("admin".equalsIgnoreCase(Session.getRole()))
                {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormDetailLostItemAdmin.fxml"));
                        Parent parent = loader.load();
                        org.oop.lostfound.controller.FormDetailLostItemAdminController controller = loader.getController();
                        controller.setLostItem(lostItem);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.setTitle("Detail Lost Item (Admin)");
                        stage.show();
                    } catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                } else
                {
                    org.oop.lostfound.controller.FormDetailLostItemController.showDetail(lostItem);
                }
            });
            imageNode = placeholder;
        }

        Label descLabel = new Label(lostItem.getDescription());
        descLabel.setStyle("-fx-text-fill: #888; -fx-font-size: 13px;");

        Label categoryLabel = new Label("Category: " + lostItem.getCategory());
        categoryLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333;");
        Label locationLabel = new Label("Location: " + lostItem.getLocation());
        locationLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333;");
        Label dateLabel = new Label("Date: " + lostItem.getDate());
        dateLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333;");

        VBox detailBox = new VBox(categoryLabel, locationLabel, dateLabel);
        detailBox.setSpacing(2);

        box.getChildren().addAll(titleRow, imageNode, descLabel, detailBox);
        return box;
    }

    private VBox createFoundItemVBox(org.oop.lostfound.model.FoundItem foundItem)
    {
        VBox box = new VBox();
        box.setSpacing(5);
        box.setAlignment(Pos.TOP_LEFT);
        box.setPrefWidth(250);
        box.setStyle("-fx-padding: 14; -fx-background-radius: 15; -fx-background-color: #fff; -fx-border-radius: 15; -fx-border-color: #eee;");

        // Row Title + Badge
        HBox titleRow = new HBox();
        titleRow.setAlignment(Pos.TOP_LEFT);
        titleRow.setSpacing(10);

        Label nameLabel = new Label(foundItem.getName());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #222;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label badge = new Label("Found");
        badge.setStyle("-fx-background-color: #e4fbe7; -fx-text-fill: #27ae60; -fx-padding: 3 16 3 16; -fx-background-radius: 10; -fx-font-size: 14px; -fx-font-weight: bold;");

        titleRow.getChildren().addAll(nameLabel, spacer, badge);

        // Image
        Node imageNode;
        if (foundItem.getImageUrl() != null && !foundItem.getImageUrl().isEmpty())
        {
            try
            {
                ImageView imageView = new ImageView(new Image(foundItem.getImageUrl(), true));
                imageView.setFitWidth(imageWidth);
                imageView.setFitHeight(imageHeight);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setStyle("-fx-background-color: #f5f5f5; -fx-border-radius: 6; -fx-cursor: hand;");
                imageView.setOnMouseClicked(e ->
                {
                    if ("admin".equalsIgnoreCase(Session.getRole()))
                    {
                        try
                        {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormDetailFoundItemAdmin.fxml"));
                            Parent parent = loader.load();
                            FormDetailFoundItemAdminController controller = loader.getController();
                            controller.setFoundItem(foundItem);
                            controller.setParentController(this);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.setTitle("Detail Found Item (Admin)");
                            stage.show();
                        } catch (Exception ex) { ex.printStackTrace(); }
                    } else {
                        org.oop.lostfound.controller.FormDetailFoundItemController.showDetail(foundItem);
                    }
                });
                imageNode = imageView;
            } catch (Exception e)
            {
                Label placeholder = new Label("No Image");
                placeholder.setPrefSize(imageWidth, imageHeight);
                placeholder.setAlignment(Pos.CENTER);
                placeholder.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #aaa; -fx-cursor: hand;");
                placeholder.setOnMouseClicked(ev -> {
                    org.oop.lostfound.controller.FormDetailFoundItemController.showDetail(foundItem);
                });
                imageNode = placeholder;
            }
        } else
        {
            Label placeholder = new Label("No Image");
            placeholder.setPrefSize(imageWidth, imageHeight);
            placeholder.setAlignment(Pos.CENTER);
            placeholder.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #aaa; -fx-cursor: hand;");
            placeholder.setOnMouseClicked(e ->
            {
                org.oop.lostfound.controller.FormDetailFoundItemController.showDetail(foundItem);
            });
            imageNode = placeholder;
        }

        Label descLabel = new Label(foundItem.getDescription());
        descLabel.setStyle("-fx-text-fill: #888; -fx-font-size: 13px;");

        Label categoryLabel = new Label("Category: " + foundItem.getCategory());
        categoryLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333;");
        Label locationLabel = new Label("Location: " + foundItem.getLocation());
        locationLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333;");
        Label dateLabel = new Label("Date: " + foundItem.getDate());
        dateLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333;");

        VBox detailBox = new VBox(categoryLabel, locationLabel, dateLabel);
        detailBox.setSpacing(2);

        box.getChildren().addAll(titleRow, imageNode, descLabel, detailBox);
        return box;
    }

    public void refreshItemList()
    {
        loadItemList();
        updateDashboardData();
    }

    @FXML
    private void shrinkImagesButtonOnAction(ActionEvent event)
    {
        if (!isShrink)
        {
            imageWidth = 1;
            imageHeight = 1;
            shrinkImagesButton.setText("Perlihatkan Gambar");
            isShrink = true;
        } else
        {
            imageWidth = 180;
            imageHeight = 90;
            shrinkImagesButton.setText("Sembunyikan Gambar");
            isShrink = false;
        }
        loadItemList();
    }

    private void loadItemList()
    {
        try
        {
            Connection connection = Connector.getConnection();
            LostItemDAO lostItemDAO = new LostItemDAO(connection);
            FoundItemDAO foundItemDAO = new FoundItemDAO();

            itemListFlowPane.getChildren().clear();

            java.util.List<?> lostItems = lostItemDAO.getAllLostItems();
            java.util.List<?> foundItems = foundItemDAO.getAllFoundItems();

            // Tampilkan LostItem
            for (var item : lostItems)
            {
                org.oop.lostfound.model.LostItem lostItem = (org.oop.lostfound.model.LostItem) item;
                itemListFlowPane.getChildren().add(createLostItemVBox(lostItem));
            }

            // Tampilkan FoundItem
            for (var item : foundItems)
            {
                org.oop.lostfound.model.FoundItem foundItem = (org.oop.lostfound.model.FoundItem) item;
                itemListFlowPane.getChildren().add(createFoundItemVBox(foundItem));
            }

            System.out.println("Loaded " + lostItems.size() + " lost items and " + foundItems.size() + " found items");

        } catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("Error loading item list: " + e.getMessage());
        }
    }

    @FXML
    public void updateDashboardData()
    {
        try {
            Connection connection = Connector.getConnection();
            LostItemDAO lostItemDAO = new LostItemDAO(connection);
            FoundItemDAO foundItemDAO = new FoundItemDAO();

            // Ambil data dari database
            int jumlahLostItems = lostItemDAO.getJumlahLostItems();
            int jumlahFoundItems = foundItemDAO.getJumlahFoundItems();
            int totalClaims = ClaimDAO.getAllClaims().size(); ; // Ganti dengan method yang sesuai jika ClaimDAO sudah ada
            int totalItems = jumlahLostItems + jumlahFoundItems;

            // Update masing-masing label dengan angka saja
            totalItemsCountLabel.setText(String.valueOf(totalItems));
            lostItemsCountLabel.setText(String.valueOf(jumlahLostItems));
            foundItemsCountLabel.setText(String.valueOf(jumlahFoundItems));
            totalClaimsCountLabel.setText(String.valueOf(totalClaims));

        } catch (Exception e)
        {
            e.printStackTrace();
            // Set nilai default jika terjadi error
            totalItemsCountLabel.setText("0");
            lostItemsCountLabel.setText("0");
            foundItemsCountLabel.setText("0");
            totalClaimsCountLabel.setText("0");
        }
    }

    @FXML
    private void lostFoundButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void lostItemButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLostItem.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void foundItemButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormFoundItem.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void reportButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormReportUser.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void claimButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormClaim.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @FXML
    private void logOutButtonOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormLogin.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
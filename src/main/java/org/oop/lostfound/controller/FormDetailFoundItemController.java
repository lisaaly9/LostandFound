package org.oop.lostfound.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.oop.lostfound.dao.FoundItemDAO;
import org.oop.lostfound.dao.ClaimItemDAO;
import org.oop.lostfound.model.Claim;
import org.oop.lostfound.model.FoundItem;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class FormDetailFoundItemController implements Initializable {
    @FXML
    private Label itemNameLabel;
    @FXML
    private Label finderNameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private ImageView itemImageView;
    @FXML
    private Button claimButton;

    private FoundItem foundItem;

    public void setFoundItem(FoundItem foundItem) {
        this.foundItem = foundItem;
        updateDetail();
    }

    private void updateDetail() {
        if (foundItem != null) {
            itemNameLabel.setText(foundItem.getNamaBarang());
            finderNameLabel.setText(foundItem.getContact());
            dateLabel.setText(foundItem.getDate() != null ? foundItem.getDate().toString() : "-");
            descriptionLabel.setText(foundItem.getDescription());
            if (foundItem.getImageUrl() != null && !foundItem.getImageUrl().isEmpty()) {
                itemImageView.setImage(new Image(foundItem.getImageUrl()));
            } else {
                itemImageView.setImage(null);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemNameLabel.setText("");
        finderNameLabel.setText("");
        dateLabel.setText("");
        descriptionLabel.setText("");
        itemImageView.setImage(null);

        if (claimButton != null) {
            claimButton.setOnAction(event -> handleClaim());
        }
    }

    public static void showDetail(FoundItem foundItem) {
        try {
            FXMLLoader loader = new FXMLLoader(FormDetailFoundItemController.class.getResource("/org/oop/lostfound/FormDetailFoundItem.fxml"));
            Parent root = loader.load();
            FormDetailFoundItemController controller = loader.getController();
            controller.setFoundItem(foundItem);
            Stage stage = new Stage();
            stage.setTitle("Detail Barang Ditemukan");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClaim() {
        // Input nama
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Klaim Barang Ditemukan");
        nameDialog.setHeaderText("Masukkan Nama Anda untuk Klaim");
        nameDialog.setContentText("Nama Anda:");
        Optional<String> nameResult = nameDialog.showAndWait();
        if (!nameResult.isPresent() || nameResult.get().trim().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Klaim Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Nama tidak boleh kosong!");
            alert.showAndWait();
            return;
        }

        // Input nomor telepon
        TextInputDialog phoneDialog = new TextInputDialog();
        phoneDialog.setTitle("Klaim Barang");
        phoneDialog.setHeaderText("Masukkan Nomor Telepon Anda");
        phoneDialog.setContentText("Nomor Telepon:");
        Optional<String> phoneResult = phoneDialog.showAndWait();
        if (!phoneResult.isPresent() || phoneResult.get().trim().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Klaim Gagal");
            alert.setHeaderText(null);
            alert.setContentText("Nomor telepon tidak boleh kosong!");
            alert.showAndWait();
            return;
        }

        // Simpan ke tabel claim_item dan hapus dari found_item
        Claim claimItem = new Claim();
        ClaimItemDAO claimItemDAO = new ClaimItemDAO();
        FoundItemDAO foundItemDAO = new FoundItemDAO();

        claimItemDAO.insertClaimItem(claimItem);
        foundItemDAO.deleteFoundItemById(foundItem.getId());

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Klaim Berhasil");
        alert.setHeaderText(null);
        alert.setContentText("Barang berhasil diklaim!");
        alert.showAndWait();

        // Tutup window detail
        Stage stage = (Stage) claimButton.getScene().getWindow();
        stage.close();
    }
}
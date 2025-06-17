package org.oop.lostfound.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.oop.lostfound.model.FoundItem;
import org.oop.lostfound.dao.FoundItemDAO;
import javafx.scene.control.Alert;

import java.util.Optional;

public class FormDetailFoundItemAdminController {

    @FXML private Label itemNameLabel;
    @FXML private ImageView itemImageView;
    @FXML private Label finderNameLabel;
    @FXML private Label dateLabel;
    @FXML private Label descriptionLabel;
    @FXML private Button deleteButton;

    private FoundItem foundItem;
    private FormMenuUtamaController parentController;

    public void setParentController(FormMenuUtamaController parentController)
    {
        this.parentController = parentController;
    }

    public void setFoundItem(FoundItem foundItem)
    {
        this.foundItem = foundItem;
        updateDetail();
    }

    @FXML
    private void initialize()
    {
        deleteButton.setOnAction(e ->
        {
            if (foundItem != null)
            {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Konfirmasi Hapus");
                confirm.setHeaderText("Hapus Report barang Found?");
                Optional<ButtonType> result = confirm.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    FoundItemDAO foundItemDAO = new FoundItemDAO();
                    boolean success = foundItemDAO.deleteFoundItemById(foundItem.getId());
                    if (success) {
                        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data barang Found berhasil dihapus.");
                        if (parentController != null) parentController.refreshItemList();
                        Stage stage = (Stage) deleteButton.getScene().getWindow();
                        stage.close();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Gagal", "Barang Found gagal dihapus.");
                    }
                }
            }
        });
    }


    private void updateDetail()
    {
        if (foundItem == null) return;
        itemNameLabel.setText(foundItem.getName());
        if (foundItem.getImageUrl() != null && !foundItem.getImageUrl().isEmpty())
        {
            itemImageView.setImage(new Image(foundItem.getImageUrl(), true));
        } else
        {
            itemImageView.setImage(null);
        }
        finderNameLabel.setText(foundItem.getContact() != null ? foundItem.getContact() : "-");
        dateLabel.setText(foundItem.getDate() != null ? foundItem.getDate().toString() : "-");
        descriptionLabel.setText(foundItem.getDescription() != null ? foundItem.getDescription() : "-");
    }

    private void showAlert(Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
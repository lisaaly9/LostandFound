package org.oop.lostfound.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import org.oop.lostfound.model.LostItem;
import java.sql.Connection;
import java.util.Optional;

public class FormDetailLostItemAdminController
{
    @FXML
    private Label itemNameLabel;
    @FXML
    private ImageView itemImageView;
    @FXML
    private Label finderNameLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Button deleteButton;

    private LostItem lostItem;
    private FormMenuUtamaController parentController;

    public void setLostItem(LostItem lostItem)
    {
        this.lostItem = lostItem;
        updateDetail();
    }

    public void setParentController(FormMenuUtamaController parentController)
    {
        this.parentController = parentController;
    }

    @FXML
    private void initialize()
    {
        deleteButton.setOnAction(e ->
        {
            if (lostItem != null)
            {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
                confirm.setTitle("Konfirmasi Hapus");
                confirm.setHeaderText("Hapus Report barang Lost?");
                Optional<ButtonType> result = confirm.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK)
                {
                    try
                    {
                        Connection conn = org.oop.lostfound.dao.Connector.getConnection();
                        org.oop.lostfound.dao.LostItemDAO lostItemDAO = new org.oop.lostfound.dao.LostItemDAO(conn);
                        boolean success = lostItemDAO.deleteLostItemById(lostItem.getId());
                        if (success)
                        {
                            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data barang Lost berhasil dihapus.");
                            if (parentController != null) parentController.refreshItemList();
                            Stage stage = (Stage) deleteButton.getScene().getWindow();
                            stage.close();
                        } else
                        {
                            showAlert(Alert.AlertType.ERROR, "Gagal", "Barang Lost gagal dihapus.");
                        }
                    } catch (Exception ex)
                    {
                        ex.printStackTrace();
                        showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + ex.getMessage());
                    }
                }
            }
        });
    }

    private void updateDetail()
    {
        if (lostItem == null) return;
        itemNameLabel.setText(lostItem.getName());
        if (lostItem.getImageUrl() != null && !lostItem.getImageUrl().isEmpty())
        {
            try
            {
                itemImageView.setImage(new Image(lostItem.getImageUrl(), true));
            } catch (Exception e)
            {
                itemImageView.setImage(null);
            }
        } else
        {
            itemImageView.setImage(null);
        }
        finderNameLabel.setText(lostItem.getContact() != null ? lostItem.getContact() : "-");
        dateLabel.setText(lostItem.getDate() != null ? lostItem.getDate().toString() : "-");
        descriptionLabel.setText(lostItem.getDescription() != null ? lostItem.getDescription() : "-");
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
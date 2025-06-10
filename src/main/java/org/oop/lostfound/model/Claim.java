package org.oop.lostfound.model;

import java.time.LocalDate;
import java.util.Date;
import org.oop.lostfound.enums.ClaimStatus;

//Constructor
public class Claim {
    private int id;
    private String itemName;
    private String finderName;
    private LocalDate foundDate;
    private String description;
    private String imageUrl;
    private String claimantName;
    private String claimantPhone;

    public void Claim(int id, String itemName, String finderName, LocalDate foundDate, String description, String imageUrl, String claimantName, String claimantPhone) {
        this.id = id;
        this.itemName = itemName;
        this.finderName = finderName;
        this.foundDate = foundDate;
        this.description = description;
        this.imageUrl = imageUrl;
        this.claimantName = claimantName;
        this.claimantPhone = claimantPhone;
    }

    public int getId() {
        return id;
    }
    public String getItemName() {
        return itemName;
    }
    public String getFinderName() {
        return finderName;
    }
    public LocalDate getFoundDate() {
        return foundDate;
    }
    public String getDescription() {
        return description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getClaimantName() {
        return claimantName;
    }
    public String getClaimantPhone() {
        return claimantPhone;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setFinderName(String finderName) {
        this.finderName = finderName;
    }
    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }
    public void setClaimantPhone(String claimantPhone) {
        this.claimantPhone = claimantPhone;
    }

}
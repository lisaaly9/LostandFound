package org.oop.lostfound.model;

import org.oop.lostfound.enums.ClaimStatus;
import java.time.LocalDate;

public class Claim {
    private int claimId;
    private LocalDate claimDate;
    private ClaimStatus status;
    private String user;
    private String itemName;

    public Claim(int claimId, LocalDate claimDate, ClaimStatus status, 
                 String user, String itemName) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.status = status;
        this.user = user;
        this.itemName = itemName;
    }

    // Getter methods
    public int getClaimId() { return claimId; }
    public void setClaimId(int claimId) { this.claimId = claimId; }

    public LocalDate getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDate claimDate) { this.claimDate = claimDate; }

    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }

    public String getuser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
}
package org.oop.lostfound.model;

import java.util.Date;
import org.oop.lostfound.enums.ClaimStatus;

//Constructor
public class Claim {
    private String claimId;
    private Date claimDate;
    private ClaimStatus claimStatus;
    private User user;
    private FoundItem item;

    public String getClaimId() { return claimId; }
    public void setClaimId(String claimId) { this.claimId = claimId; }

    public Date getClaimDate() { return claimDate; }
    public void setClaimDate(Date claimDate) { this.claimDate = claimDate; }

    public ClaimStatus getClaimStatus() { return claimStatus; }
    public void setClaimStatus(ClaimStatus claimStatus) { this.claimStatus = claimStatus; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public FoundItem getItem() { return item; }
    public void setItem(FoundItem item) { this.item = item; }
}
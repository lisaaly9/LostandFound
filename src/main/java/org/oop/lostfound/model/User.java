package org.oop.lostfound.model;

//import java.util.ArrayList;

public class User {
   private int id;
    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
    // getter
    public int getId() { return id; }
    public String getUsername() { return username; }

    // public void tambahClaim(Claim claim) {
    //     daftarClaim.add(claim);
    // }

    // public ArrayList<Claim> getDaftarClaim() {
    //     return daftarClaim;
    // }
}

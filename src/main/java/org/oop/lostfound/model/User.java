package org.oop.lostfound.model;

import java.util.ArrayList;

public class User {
    private int userId;
    private String nama;
    private ArrayList<Claim> daftarClaim;

    public User(int userId, String nama) {
        this.userId = userId;
        this.nama = nama;
        this.daftarClaim = new ArrayList<>();
    }

    public void tambahClaim(Claim claim) {
        daftarClaim.add(claim);
    }

    public ArrayList<Claim> getDaftarClaim() {
        return daftarClaim;
    }
}

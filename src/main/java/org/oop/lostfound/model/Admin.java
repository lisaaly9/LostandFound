package org.oop.lostfound.model;

public class Admin extends User
{
    private int adminLevel;

    public Admin(int id, String username, String phone, String email, String password, int adminLevel)
    {
        super(id, username, phone, email, password);
        this.adminLevel = adminLevel;
    }

}
package org.oop.lostfound.model;

public class User
{
    protected int id;
    protected String username;
    protected String phone;
    protected String email;
    protected String password;

    public User(int id, String username, String phone, String email, String password)
    {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
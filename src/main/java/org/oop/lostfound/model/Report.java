package org.oop.lostfound.model;

import java.time.LocalDate;

public class Report {
    private int reportId;
    private String user;
    private String itemName;
    private String type;
    private String location;
    private LocalDate date;
    private String contact;

    public Report(int reportId, String user, String itemName, String type, String location, LocalDate date, String contact) {
        this.reportId = reportId;
        this.user = user;
        this.itemName = itemName;
        this.type = type;
        this.location = location;
        this.date = date;
        this.contact = contact;
    }

    public String getUser() {return user;}
    public void setUser(String user) {this.user = user;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    public String getContact() {return contact;}
    public void setContact(String contact) {this.contact = contact;}
}

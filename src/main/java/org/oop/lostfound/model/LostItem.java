package org.oop.lostfound.model;

import java.time.LocalDate;
import org.oop.lostfound.enums.Category;

public class LostItem extends Item implements IReportable {
    
    // Konstruktor dengan parameter (existing)
    public LostItem(int id, String name, String description, String location, 
                    Category category, String contact, String imageUrl, LocalDate date) {
        super(id, name, description, location, category, contact, imageUrl, date);
    }
    
    // Tambahkan konstruktor default untuk DAO
    public LostItem() {
        super(0, "", "", "", null, "", "", null);
    }

    @Override
    public String generateReport() {
        return "Laporan Kehilangan: " + getName() + " di lokasi " + getLocation();
    }

    @Override
    public String getDetails() {
        return "[LOST] " + getName() + " - " + getDescription();
    }

    public void setDetails(String name, String description, String location, 
                          Category category, String contact, String imageUrl, LocalDate date) {
        setName(name);
        setDescription(description);
        setLocation(location);
        setCategory(category);
        setContact(contact);
        setImageUrl(imageUrl);
        setDate(date);
    }

    // Getter methods yang diperlukan untuk controller
    public String getNamaBarang() {
        return getName(); // menggunakan method dari parent class
    }
    
    public String getImageUrl() {
        return super.getImageUrl(); // menggunakan method dari parent class
    }
    
    // Tambahkan setter methods yang diperlukan untuk DAO
    public void setNamaBarang(String name) {
        setName(name);
    }
    
   
    
    public void setDescription(String description) {
        super.setDescription(description);
    }
    
    public void setLocation(String location) {
        super.setLocation(location);
    }
    
    public void setContact(String contact) {
        super.setContact(contact);
    }
    
    public void setImageUrl(String imageUrl) {
        super.setImageUrl(imageUrl);
    }
    
    public void setDate(LocalDate date) {
        super.setDate(date);
    }
    
    public void setCategory(Category category) {
        super.setCategory(category);
    }
    
    public void setId(int id) {
        super.setId(id);
    }
    public int getId() {
        return super.getId(); 
    }
    public String getContact() {
        return super.getContact(); 
    }
    public LocalDate getDate() {
        return super.getDate();
    }
    public Category getCategory() {
        return super.getCategory(); 
    }
    public String getLocation() {
        return super.getLocation(); 
    }
    public String getDescription() {
        return super.getDescription(); 
    }
    public String getName() {
        return super.getName(); 
    }
    public void setName(String name) {
        super.setName(name); 
    }
    public void setId(String id) {
        super.setId(Integer.parseInt(id)); 
    }
    
}
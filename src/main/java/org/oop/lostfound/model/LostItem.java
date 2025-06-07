package org.oop.lostfound.model;

import java.time.LocalDate;
import org.oop.lostfound.enums.Category;
import java.sql.Date;
import java.sql.Timestamp;

public class LostItem extends Item implements IReportable {
    
    public LostItem(int id, String name, String description, String location,
                    Category category, String contact, String imageUrl, LocalDate date) {
        super(id, name, description, location, category, contact, imageUrl, date);
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

    public String getNamaBarang() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    
}

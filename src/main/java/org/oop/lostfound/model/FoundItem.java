package org.oop.lostfound.model;

import java.util.Date;

import org.oop.lostfound.enums.Category;
import org.oop.lostfound.enums.ItemType;
import org.oop.lostfound.model.Item;

class FoundItem extends Item implements IReportable
{
    private String finderId;
    private String image;

    public String getFinderId() { return finderId; }
    public void setFinderId(String finderId) { this.finderId = finderId; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    @Override
    public String generateReport()
    {
        return "Laporan Penemuan: " + getName() + " oleh " + finderId;
    }

    @Override
    public String getDetails()
    {
        return "[FOUND] " + getName() + " - " + getDescription() + " (image: " + image + ")";
    }

    @Override
    public void setDetails(String name, String description, String location, Category category, Date date)
    {
        setName(name);
        setDescription(description);
        setLocation(location);
        setCategory(category);
        setDate(date);
        setItemType(ItemType.FOUND);
    }
    
}

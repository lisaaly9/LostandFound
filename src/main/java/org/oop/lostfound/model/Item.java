package org.oop.lostfound.model;

import java.util.Date;
import org.oop.lostfound.enums.*;

abstract class Item {
    private String itemId;
    private String name;
    private String description;
    private String location;
    private Date date;
    private Category category;
    private ItemType itemType;

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public ItemType getItemType() { return itemType; }
    public void setItemType(ItemType itemType) { this.itemType = itemType; }

    public abstract String getDetails();
    public abstract void setDetails(String name, String description, String location, Category category, Date date);
}

package org.oop.lostfound.model;

import java.util.Date;

import org.oop.lostfound.enums.Category;
import org.oop.lostfound.enums.ItemType;
import org.oop.lostfound.model.Item;

public class LostItem extends Item implements IReportable {
    @Override
    public String generateReport() {
        return "Laporan Kehilangan: " + getName() + " di lokasi " + getLocation();
    }

    @Override
    public String getDetails() {
        return "[LOST] " + getName() + " - " + getDescription();
    }

    @Override
    public void setDetails(String name, String description, String location, Category category, Date date)
    {
        setName(name);
        setDescription(description);
        setLocation(location);
        setCategory(category);
        setDate(date);
        setItemType(ItemType.LOST);
    }

}

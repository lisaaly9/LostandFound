package org.oop.lostfound.model;

public class FoundItem extends Item implements IReportable {

    public FoundItem() {
        super(0, "", "", "", null, "", "", null);
    }

    @Override
    public String generateReport()
    {
        return "Laporan Penemuan: " + getName() + " ditemukan di lokasi " + getLocation();
    }

    @Override
    public String getDetails() {
        return "[FOUND] " + getName() + " - " + getDescription();
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

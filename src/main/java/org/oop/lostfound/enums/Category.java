package org.oop.lostfound.enums;

<<<<<<< HEAD
public enum Category { ELEKTRONIK, DOKUMEN, ALAT_TULIS, LAINNYA;
=======
public enum Category { ELEKTRONIK, DOKUMEN, ALAT_TULIS, LAINNYA,;
>>>>>>> 200c5db3b83506382f1f5964e262caeaebadcbfb

     public boolean equalsIgnoreCase(Category other) {
        return other != null && this.name().equalsIgnoreCase(other.name());
    }

    public boolean equalsIgnoreCase(String other) {
        return other != null && this.name().equalsIgnoreCase(other);
    }


    @Override
    public String toString() {
        switch (this) {
            case ELEKTRONIK: return "Elektronik";
            case DOKUMEN: return "Dokumen";
            case ALAT_TULIS: return "Alat Tulis";
            case LAINNYA: return "Lainnya";
            default: return name();
        }
    }
}
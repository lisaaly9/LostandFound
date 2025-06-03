module org.oop.lostfound {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.oop.lostfound to javafx.fxml;
    exports org.oop.lostfound;
    exports org.oop.lostfound.controller;
    opens org.oop.lostfound.controller to javafx.fxml;
}
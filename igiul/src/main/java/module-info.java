module com.igiul {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.igiul to javafx.fxml;
    exports com.igiul;
}

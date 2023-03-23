module com.igiul {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.igiul to javafx.fxml;
    exports com.igiul;
}

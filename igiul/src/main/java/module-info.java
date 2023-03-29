module com.igiul {
    requires transitive javafx.graphics; 
    requires javafx.controls;
    requires java.desktop;

    opens com.igiul to javafx.fxml;
    exports com.igiul;
}
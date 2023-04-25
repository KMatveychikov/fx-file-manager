module com.example.fxfilemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.fxfilemanager to javafx.fxml;
    exports com.example.fxfilemanager;
}
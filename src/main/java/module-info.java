module com.example.dicesplit {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.dicesplit to javafx.fxml;
    exports com.example.dicesplit;
}
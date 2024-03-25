module org.example.sapper {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sapper to javafx.fxml;
    exports org.example.sapper;
}
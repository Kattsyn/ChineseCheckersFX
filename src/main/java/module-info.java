module com.example.chinesecheckersfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chinesecheckersfx to javafx.fxml;
    exports com.example.chinesecheckersfx;
}
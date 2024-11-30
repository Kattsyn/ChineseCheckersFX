module com.example.chinesecheckersfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.chinesecheckersfx to javafx.fxml;
    exports com.chinesecheckersfx;
}
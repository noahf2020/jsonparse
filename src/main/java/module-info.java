module com.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;


    opens com.example.demo2 to javafx.fxml;
    opens com.example.demo2.Sportsevents;


    exports com.example.demo2;

}
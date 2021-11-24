module com.example.front_spring_tp4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jersey.client;
    requires javax.ws.rs.api;
    requires json.simple;

    opens com.example.front_spring_tp4 to javafx.fxml;
    exports com.example.front_spring_tp4;
}
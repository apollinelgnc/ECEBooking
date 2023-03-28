module com.example.ecebooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ecebooking to javafx.fxml;
    exports com.example.ecebooking;
    exports com.example.ecebooking.Controllers;
    exports com.example.ecebooking.Controllers.Admin;
    exports com.example.ecebooking.Controllers.Client;
    exports com.example.ecebooking.Models;
    exports com.example.ecebooking.Views;
}
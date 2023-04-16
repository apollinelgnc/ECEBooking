module com.example.ecebooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.kordamp.bootstrapfx.core;
    requires de.jensd.fx.glyphs.materialstackicons;

    opens com.example.ecebooking to javafx.fxml;
    opens com.example.ecebooking.Controllers.Client to javafx.fxml;
    opens com.example.ecebooking.Controllers.Hebergements to javafx.fxml;
    exports com.example.ecebooking;
    exports com.example.ecebooking.Controllers;
    exports com.example.ecebooking.Controllers.Admin;
    exports com.example.ecebooking.Controllers.Client;
    exports com.example.ecebooking.Controllers.Hebergements;
    exports com.example.ecebooking.Models;
    exports com.example.ecebooking.Views;
    opens com.example.ecebooking.Controllers.Admin; // Ouvrir le package pour permettre l'accès depuis JavaFX


}
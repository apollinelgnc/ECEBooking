module com.example.ecebooking {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.ecebooking to javafx.fxml;
    exports com.example.ecebooking;
}
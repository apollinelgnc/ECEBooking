package com.example.ecebooking.Controllers.Client;


import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class MenuController {

    public TextField destination=new TextField();
    public ChoiceBox<Integer> nb_persons=new ChoiceBox<>();
    public DatePicker check_in_date=new DatePicker();
    public DatePicker check_out_date=new DatePicker();
    public Button go=new Button();
    public TextField prix=new TextField();
    public ChoiceBox<Integer> nombre_chambres=new ChoiceBox<>();
    public TextField distance=new TextField();
    public TextField nom_hebergement;
    Hebergement hebergement;



    public void initialize(){
        nb_persons.getItems().add(1);
        nb_persons.getItems().add(2);
        nb_persons.getItems().add(3);
        nb_persons.getItems().add(4);
        nb_persons.getItems().add(5);
        nb_persons.getItems().add(6);
        nombre_chambres.getItems().add(1);
        nombre_chambres.getItems().add(2);
        nombre_chambres.getItems().add(3);
        nombre_chambres.getItems().add(4);
        nombre_chambres.getItems().add(5);
        nombre_chambres.getItems().add(6);

        go.setOnAction(event -> {
            System.out.println(nombre_chambres.getSelectionModel().getSelectedItem());
            try {
                hebergement=new Hebergement(nom_hebergement.getText(),
                        destination.getText(),
                        nombre_chambres.getSelectionModel().getSelectedItem(),
                        nb_persons.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(prix.getText()),
                        Integer.parseInt(distance.getText()));
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

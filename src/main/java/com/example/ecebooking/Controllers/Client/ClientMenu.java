package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientMenu implements Initializable {
    public Button menu_button = new Button();
    public Button reservation_button;
    public Button stats_button;
    public Button profile_button;
    public Button log_out_button;
    public Button go_button = new Button();
    public ChoiceBox<Integer> nb_personnes = new ChoiceBox<>();
    public TextField destination;
    public DatePicker check_in_date;
    public DatePicker check_out_date;
    private int choix_nb_personne;
    private LocalDate check_in;
    private LocalDate check_out;
    private String desti;
    private ArrayList<Hebergement> hebergements=new ArrayList<>();


    public void initialize(URL url, ResourceBundle resourceBundle){
        nb_personnes.getItems().addAll(1,2,3,4,5,6,7);
        go_button.setOnAction(event -> {
            if(nb_personnes.getSelectionModel().getSelectedItem()==null)
                setChoix_nb_personne(0);
            else setChoix_nb_personne(nb_personnes.getSelectionModel().getSelectedItem());
            if(check_out_date.getValue()==null)
                setCheck_out(LocalDate.ofEpochDay(0));
            else setCheck_in(check_in=check_in_date.getValue());
            if(check_in_date.getValue()==null)
                setCheck_in(LocalDate.ofEpochDay(0));
            else setCheck_out(check_out=check_out_date.getValue());
            setDesti(desti =destination.getText());
            System.out.println(desti + choix_nb_personne + check_in + check_out);
            Model.getInstance().getViewFactory().ClientView();
        });
    }


    public int getChoix_nb_personne() {
        return choix_nb_personne;
    }

    public void setChoix_nb_personne(int choix_nb_personne) {
        this.choix_nb_personne = choix_nb_personne;
    }

    public LocalDate getCheck_in() {
        return check_in;
    }

    public void setCheck_in(LocalDate check_in) {
        this.check_in = check_in;
    }

    public LocalDate getCheck_out() {
        return check_out;
    }

    public void setCheck_out(LocalDate check_out) {
        this.check_out = check_out;
    }

    public String getDesti() {
        return desti;
    }

    public void setDesti(String desti) {
        this.desti = desti;
    }
}

package com.example.ecebooking.Controllers;

import com.example.ecebooking.Models.DataCo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {

    static int nombre_reservation;
    private int id_hebergement;
    private int id_client;
    private LocalDate debut;
    private LocalDate fin;

    //public Reservation(int id_hebergement, int id_client, Dates date_debut, Dates date_fin) {
    public Reservation(int id_hebergement, int id_client, LocalDate date_debut, LocalDate date_fin) {
        this.id_hebergement = id_hebergement;
        this.id_client = id_client;
        this.debut = date_debut;
        this.fin = date_fin;
    }

    public static int getNombre_reservation() {
        return nombre_reservation;
    }

    public int getId_hebergement() {
        return id_hebergement;
    }

    public int getId_client() {
        return id_client;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public boolean verification() throws SQLException, ClassNotFoundException {
        DataCo dataco = new DataCo();
        ArrayList<Reservation> bdd_reservation = dataco.SQL_Data_Reservation(this.id_hebergement);

        for (Reservation reservation : bdd_reservation) {
            if (reservation.getId_hebergement() == this.id_hebergement) {
                if (reservation.debut.isBefore(this.debut) && this.debut.isBefore(reservation.fin)) {
                    System.out.println(reservation.debut + " " + this.debut + " " + reservation.fin);
                    return false;
                }
                if (reservation.debut.isBefore(this.fin) && this.fin.isBefore(reservation.fin)) {
                    System.out.println(reservation.debut + " " + this.fin + " " + reservation.fin);
                    return false;
                }
                if(this.debut.isBefore(reservation.debut) && reservation.fin.isBefore(this.fin)) {
                    System.out.println(this.debut+ " " + reservation.debut + " " + reservation.fin + " " + this.fin);
                    return false;
                }
            }
        }
        return true;
    }

    public void afficher()
    {
        System.out.println(this.id_hebergement + " " + this.id_client + " " + this.debut + " " + this.fin);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_hebergement=" + id_hebergement +
                ", id_client=" + id_client +
                ", date_debut='" + debut + '\'' +
                ", date_fin='" + fin + '\'' +
                '}';
    }
}

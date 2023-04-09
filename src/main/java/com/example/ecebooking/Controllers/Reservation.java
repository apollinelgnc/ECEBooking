package com.example.ecebooking.Controllers;

public class Reservation {

    static int nombre_reservation;
    private int id_hebergement;
    private int id_client;
    private String date_debut;
    private String date_fin;

    public Reservation(int id_hebergement, int id_client, String date_debut, String date_fin) {
        this.id_hebergement = id_hebergement;
        this.id_client = id_client;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id_hebergement=" + id_hebergement +
                ", id_client=" + id_client +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                '}';
    }
}

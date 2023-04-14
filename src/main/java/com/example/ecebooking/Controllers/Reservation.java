package com.example.ecebooking.Controllers;

import com.example.ecebooking.Models.DataCo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Reservation {
    final int id_hebergement;
    final double prix;
    final int id_client;
    final LocalDate debut;
    final LocalDate fin;

    public Reservation(int id_hebergement, int id_client, LocalDate date_debut, LocalDate date_fin, double prix) {
        this.id_hebergement = id_hebergement;
        this.id_client = id_client;
        this.debut = date_debut;
        this.fin = date_fin;
        this.prix = prix;
    }

    public int getId_hebergement() {
        return id_hebergement;
    }
    public int getId_client() {
        return id_client;
    }
    public LocalDate getDebut() {return debut;}
    public LocalDate getFin() {return fin;}
    public double getPrix() {return prix;}

   /* public boolean verification() throws SQLException, ClassNotFoundException {
        DataCo dataco = new DataCo();
        ArrayList<Reservation> bdd_reservation = dataco.SQL_Data_Reservation();

        for(int i=0; i<bdd_reservation.size(); i++)
        {
            if(bdd_reservation.get(i).getId_hebergement() != this.id_hebergement)
            {
                bdd_reservation.remove(i);
                i--;
            }
        }

        if(debut.equals(fin))
        {
            System.out.println("Les reservation doivent être au moins de 24h");
            return false;
        }

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
*/
    public void afficher()
    {
        System.out.println(this.id_hebergement + "\n" + this.id_client + "\n" + this.debut + "\n" + this.fin + "\n" + this.prix);
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

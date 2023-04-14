package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Reservation;

import java.time.LocalDate;

public class Client extends Invite{

    static int compteur_client; // nombre de client total
    protected String nom;
    protected String utilisateur;
    protected String mdp;
    protected int id; // numero specific du client
    protected double reduction;

    /** CONSTRUCTEURS */

    // création par l'utilisateur
   public Client(){
       super();
    }

    // constructeur base de donnees
    public Client (String nom,String utilisateur,String mdp,int id) {
        super();
        this.nom = nom;
        this.utilisateur = utilisateur;
        this.mdp = mdp;
        this.id = id;
        reduction = 0.9;
        compteur_client++;
    }

    public Reservation creerReservation(int i, LocalDate debut, LocalDate fin, double prix)
    {
        prix = prix * getReduction();
        return new Reservation(i,this.getId(),debut,fin,prix);
    }

    @Override
    public String toString() {
        return "Client{" +
                "utilisateur='" + utilisateur + '\'' +
                ", mdp='" + mdp + '\'' +
                ", id=" + id +
                '}';
    }

    public String getMdp() {return mdp;}
    public double getReduction() {return reduction;}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public String getUtilisateur() {return utilisateur;}
}

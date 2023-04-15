package com.example.ecebooking.Controllers.Client;

import com.example.ecebooking.Controllers.Reservation;
import com.example.ecebooking.Models.DataCo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
    public Client (String nom,String utilisateur,String mdp,int id, double reduction) {
        super();
        this.nom = nom;
        this.utilisateur = utilisateur;
        this.mdp = mdp;
        this.id = id;
        this.reduction = reduction;
        compteur_client++;
    }

    public Reservation creerReservation(int i, LocalDate debut, LocalDate fin, double prix)
    {
        System.out.println(getReduction());
        prix = prix * getReduction();
        System.out.println(prix);
        return new Reservation(i,this.getId(),debut,fin,prix,-1);
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
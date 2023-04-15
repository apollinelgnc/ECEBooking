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

    public void menu() throws SQLException, ClassNotFoundException {


        String choix;
        Scanner clavier = new Scanner(System.in);

        do {
            System.out.println("\n======= Menu Client ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Faire une reservation");
            System.out.println("2. Afficher mes réservations");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci");
                case "1" -> reserver();
                case "2" -> afficherReservation();
                default -> {
                }
            }
        } while (!choix.equals("0"));
    }

    public Reservation creerReservation(int i, LocalDate debut, LocalDate fin, double prix)
    {
        prix = prix * getReduction();
        return new Reservation(i,this.getId(),debut,fin,prix,-1);
    }

    public void afficherReservation() throws SQLException, ClassNotFoundException {

        DataCo dataCo = new DataCo();
        ArrayList<Reservation> ListeReservation = dataCo.SQL_Data_Reservation();

        for(Reservation resa : ListeReservation)
        {
            if(this.id == resa.getId_client())
            {
                System.out.println();
                resa.afficher();
            }
        }
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

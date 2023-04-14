package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Client {

    //static int compteur_client; // nombre de client total
    protected String nomA;
    protected String idA;
    protected String mdpA;
    protected int numeroA; // numero specific du client
    protected double reductionA;


    public Admin (String nom, String id, String mdp, int numero) {
        super();
        this.nomA = nom;
        this.idA = id;
        this.mdpA = mdp;
        this.numeroA = numero;
        reductionA = 0.9;
    }

    public String getIdA() {
        return idA;
    }

    public void setIdA(String id) {
        this.idA = id;
    }

    public String getMdpA() {
        return mdpA;
    }

    public void setMdpA(String mdp) {
        this.mdpA = mdp;
    }

    public int getNumeroA() {
        return numeroA;
    }

    public void setNumeroA(int numero) {
        this.numeroA = numero;
    }



    public String getNomA() {
        return nomA;
    }
    public void setNomA(String nom) {
        this.nomA = nom;
    }

    /*public void creerHebergement()
    {
        hebergementListe.add(new Hebergement());
    }*/

    public void menuAdmin() throws SQLException, ClassNotFoundException {
        String choix;
        Scanner clavier = new Scanner(System.in);

        do {
            ArrayList<Client> COListe = new ArrayList<>();
            ArrayList<Admin> ADListe = new ArrayList<>();
            ArrayList<Hebergement> hebergementListe = new ArrayList<>();
            DataCo dataco = new DataCo();
            dataco.SQL_Data_Login(COListe);
            //dataco.afficherListeClient(COListe);
            dataco.SQL_Data_Admin(ADListe);
            dataco.SQL_Data_Hebergements2(hebergementListe);

            System.out.println("\n======= Menu Admin ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Gerer hebergement");
            System.out.println("2. Gerer à la liste des clients");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci0");
                case "1" -> gererHebrgement(hebergementListe); //gererHebrgement();
                case "2" -> gererClient();
                default -> {
                }
            }
        } while (!choix.equals("0"));


        }

        public void gererHebrgement(ArrayList<Hebergement> hebergementListe){
            //afficherListeHebergements(hebergementListe);
        }

          /*  public void afficherListeHebergements(ArrayList<Hebergement> liste) {
                // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
                for (Hebergement h : liste) {
                    System.out.println("Hébergement : " + h.getIdhebergement());
                    System.out.println("Nom : " + h.getNom_etablissement());
                    System.out.println("Ville : " + h.getVille());
                    System.out.println("Prix : " + h.getPrix());
                    // ... afficher d'autres attributs selon votre structure de données
                    System.out.println("--------------------");
                }
            }*/
        public void gererClient(){

        }

}

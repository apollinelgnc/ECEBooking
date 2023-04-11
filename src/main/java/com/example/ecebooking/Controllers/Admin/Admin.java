package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.DataCo;
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


    public Admin (String nom, String id, String mdp, int numero, ArrayList<Hebergement> hebergementListe) {
        super(hebergementListe);
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
            //ArrayList<Hebergement> hebergementListe = new ArrayList<>();
            DataCo dataco = new DataCo();
            dataco.SQL_Data_Login(COListe);
            dataco.afficherListeClient(COListe);
            dataco.SQL_Data_Admin(ADListe);

            System.out.println("\n======= Menu Admin ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Gerer hebergement");
            System.out.println("2. Gerer à la liste des clients");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci0");
                case "1" -> System.out.println("Merci1"); //gererHebrgement();
                case "2" -> System.out.println("Merci2");
                default -> {
                }
            }
        } while (!choix.equals("0"));

        /*public void gererHebrgement(){
            }*/
        }

}

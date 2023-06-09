package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Client.Reservation;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.sql.SQLException;
import java.util.ArrayList;

public class Admin {
    protected String nomA;
    protected String idA;
    protected String mdpA;
    protected int numeroA; // numero specific du client
    protected double reductionA;
    DataCo dataco = new DataCo();
    ArrayList<Client> COListe = new ArrayList<>();
    ArrayList<Hebergement> hebergementListe = new ArrayList<>();
    ArrayList<Admin> ADListe = new ArrayList<>();
    ArrayList<Reservation> ListeReservation = new ArrayList<>();


    /**
     * GETTERS / SETTERS
     */

    public Admin(String nom, String id, String mdp, int numero) {
        super();
        this.nomA = nom;
        this.idA = id;
        this.mdpA = mdp;
        this.numeroA = numero;
        reductionA = 0.9;
    }

    /**
     * METHODES
     */

    public void AdminChargement() throws Exception {
        dataco.SQL_Data_Admin(ADListe);
        if (ListeReservation == null) {
            ListeReservation = dataco.SQL_Data_Reservation();
        } else {
            ListeReservation.clear();
            ListeReservation = dataco.SQL_Data_Reservation();
        }
        if (hebergementListe == null) {
            dataco.SQL_Data_Hebergements2(hebergementListe);
        } else {
            hebergementListe.clear();
            dataco.SQL_Data_Hebergements2(hebergementListe);
        }
        if (COListe == null)
            dataco.SQL_Data_Login(COListe);
        else {
            COListe.clear();
            dataco.SQL_Data_Login(COListe);
        }
    }

    public ArrayList<Client> getListClient() {
        return COListe;
    }

    public ArrayList<Hebergement> getListHebergement() {
        return hebergementListe;
    }

    public ArrayList<Reservation> getListResa() {
        return ListeReservation;
    }

    //SP menu de l admin


    //SP pour l afficahge de toute la liste des hebergments de la bdd
    public void afficherListeHebergements(ArrayList<Hebergement> liste) {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        for (Hebergement h : liste) {
            System.out.println("Hébergement : " + h.getIdhebergement());
            System.out.println("Nom : " + h.getNom_etablissement());
            System.out.println("Ville : " + h.getVille());
            System.out.println("Nombres de Chambres : " + h.getNombre_chambres());
            System.out.println("Nombres de places : " + h.getNombre_places());
            System.out.println("Prix : " + h.getPrix());                            //affichage des attribut de chacun des nos hebergments
            System.out.println("Distance Centre : " + h.getDistanceCentre());
            System.out.println("Wifi : " + h.getWifi());
            System.out.println("Menage : " + h.getMenage());
            System.out.println("Fumeur : " + h.getFumeur());

            System.out.println("--------------------");
        }
    }

    //SP de suppresion d un hebergment
    public void SuppHergement(String choix) throws SQLException, ClassNotFoundException {
        choix = "'" + choix + "'";            //choix de l id de l hebergment a supp
        dataco.Data_Supp_Hebergement(choix);            //envoie de l id de l hebergment a supprimer pour creee la bonne requete sql
    }

    //SP de mise a jour des donne d un hebergment
    public void ActuHergement(String id, String nom_etablissement, String ville, int nombre_chambres, int nombre_places, int prix, int distanceCentre, String wifi, String menage, String fumeur) throws SQLException, ClassNotFoundException {
        String S2 = "";
        S2 = S2 + " `nom`='" + nom_etablissement + "'";         //creation de la string pour realiser la requete de maj apres
        S2 = S2 + ", `ville`= '" + ville + "'";   //creation de la string pour realiser la requete de maj apres
        S2 = S2 + ", `nbChambre`='" + nombre_chambres + "'";  //creation de la string pour realiser la requete de maj apres
        S2 = S2 + ", `nbPlace`='" + nombre_places + "'";  //creation de la string pour realiser la requete de maj apres
        S2 = S2 + ", `prix`='" + prix + "'";  //creation de la string pour realiser la requete de maj apres
        S2 = S2 + ", `distanceCentre`='" + distanceCentre + "'";  //creation de la string pour realiser la requete de maj apres
        if (wifi.equals("oui"))      //creation de la string pour la requete en fonction de la reponse du blindage
            S2 = S2 + ",  `wifi`='1'";
        else
            S2 = S2 + ", `wifi`='0'";        //creation de la string pour realiser la requete de maj apres

        if (menage.equals("oui"))        //creation de la string pour la requete en fonction de la reponse du blindage
            S2 = S2 + ",  `menage`='1'";        //creation de la string pour realiser la requete de maj apres

        else           //creation de la string pour la requete en fonction de la reponse du blindage
            S2 = S2 + ", `menage`='0'";     //creation de la string pour realiser la requete de maj apres

//option de fumeur
        if (fumeur.equals("oui"))        //creation de la string pour la requete en fonction de la reponse du blindage
            S2 = S2 + ",  `fumeur`='1'";     //creation de la string pour realiser la requete de maj apres
        else          //creation de la string pour la requete en fonction de la reponse du blindage
            S2 = S2 + ", `fumeur`='0'";      //creation de la string pour realiser la requete de maj apres
        String S1 = "UPDATE `etablissement` SET ";        //debut la requete pour faire la maj
        String S3 = " WHERE `id`=";

        System.out.println("-- entrer l'id du client --");      //choix de l hebergment a mettre a jour
        id = "'" + id + "'";
        S1 = S1 + S2 + S3 + id;          //creation de la requete pour realiser la maj en fonction de l id donne
        System.out.println(S1);
        dataco.Data_Actu_Hebergement(S1);           //envoie de la requete ou SP qui executera la requete dans la bdd
    }

    //SP de saisit de la promotion a mettre a jour pour un herbegement en particulier
    public void PromoH(String id, String choix) throws SQLException, ClassNotFoundException {
        dataco.Data_Promo_Hebergement(id, choix);  //envoie d'une partie avec la promo et l id de l hebergment a modif au SP qui executera la requete dans la bdd
    }

    //SP de saisit des attributs pour ajouter un herbegement
    public void AjoutHbergement(String ville, String nom, String prix, String distance, String nombrechambres, String nombreplaces, String SdistanceCentre, String Wifi1, String Menage1, String fumeur1) throws SQLException, ClassNotFoundException {
        int num = hebergementListe.size() + 1;
        dataco.Data_Ajout_Hebergement(nom, ville, nombrechambres, nombreplaces, prix, SdistanceCentre, Wifi1, Menage1, fumeur1, num);
    }

    /**
     * Client
     */

//menu pour gere les options de clients


    //SP affichage de la liste des clients
    public void afficherListeClient(ArrayList<Client> liste) {
        // Parcourir la liste d'client et afficher les informations de chaque client
        for (Client C : liste) {
            System.out.println("Nom d utilisateur : " + C.getNom());
            System.out.println("Pseudo d utilisateur : " + C.getUtilisateur());
            System.out.println("Mdp du client  : " + C.getMdp());           //afficaheg de chaque attribut de chauqe client
            System.out.println("ID du client : " + C.getId());

            System.out.println("--------------------");
        }
    }

    //SP de suppresion d un client dans la bdd
    public void SuppClient(String choix) throws SQLException, ClassNotFoundException {
        choix = "'" + choix + "'";                //creation de la string pour realiser la requete de suppresion
        dataco.Data_Supp_Client(choix);         //envoie de la string de l id de l hebergment pour faire la requete de suppresion
    }

    //SP de maj de la promotion d un client dans la bdd
    public void PromoC(String id, String promo) throws SQLException, ClassNotFoundException {
        dataco.Data_Promo_Client(id, promo);           //envoie des strinf de l id client et la promo pour realiser la requete sql et update la bdd

    }

    //SP d affichage de la liste des reservation faites
    public void AfficherReservation() throws SQLException, ClassNotFoundException {

        for (Reservation resa : ListeReservation)     //parcours de la liste de reservation
        {
            resa.afficher();         //affichage de tout les attribut de resveration
        }
    }

    //SP de suppresion d une resa dnas la liste des ras realiser
    public void SuppReza(String choix) throws SQLException, ClassNotFoundException {
        choix = "'" + choix + "'";
        dataco.Data_Supp_Reza(choix);       //envoie de la string de l id de la resa pour faire la requete de suppresion
    }

    public String getIdA() {
        return idA;
    }

    public String getMdpA() {
        return mdpA;
    }

    public int getNumeroA() {
        return numeroA;
    }

    public String getNomA() {
        return nomA;
    }

}






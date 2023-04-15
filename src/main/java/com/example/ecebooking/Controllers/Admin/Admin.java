package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
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

    DataCo dataco = new DataCo();
    ArrayList<Client> COListe = new ArrayList<>();
    ArrayList<Hebergement> hebergementListe = new ArrayList<>();
    ArrayList<Admin> ADListe = new ArrayList<>();

    public void menuAdmin() throws SQLException, ClassNotFoundException {
        String choix;
        Scanner clavier = new Scanner(System.in);
        do {
            //dataco.afficherListeClient(COListe);
            dataco.SQL_Data_Admin(ADListe);

            System.out.println("\n======= Menu Admin ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Gerer hebergement");
            System.out.println("2. Gerer à la liste des clients");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {
                case "0" -> System.out.println("Merci0");
                case "1" -> gererHebrgement(); //gererHebrgement();
                case "2" -> gererClient();
                default -> {
                }
            }
        } while (!choix.equals("0"));


    }

    public void gererHebrgement() throws SQLException, ClassNotFoundException {
        dataco.SQL_Data_Hebergements2(hebergementListe);
        String choixH;
        Scanner clavier = new Scanner(System.in);
        do{
        System.out.println("\n======= Menu Admin ======\n");
        System.out.println("0. Quitter");
        System.out.println("1. Afficher la liste des hebergement");
        System.out.println("2. Supprimer un hebergement");
        System.out.println("3. Acutaliser un hebergement");
        System.out.println("4. Ajouter une reduction a un hebergement");
        System.out.println("5. Ajouter un hebergement");
        System.out.print("\nsaisir menu : ");
            choixH = clavier.next();
            switch (choixH) {
                case "0" -> System.out.println("Merci");
                case "1" -> afficherListeHebergements(hebergementListe);//gererHebrgement();
                case "2" -> SuppHergement();
                case "3" -> ActuHergement();//gererHebrgement();
                case "4" -> PromoH();
                case "5" -> AjoutHbergement();
                default -> {
                }
            }
        } while (!choixH.equals("0"));
    }

    public void afficherListeHebergements(ArrayList<Hebergement> liste) {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        for (Hebergement h : liste) {
            System.out.println("Hébergement : "+ h.getIdhebergement());
            System.out.println("Nom : " + h.getNom_etablissement());
            System.out.println("Ville : " + h.getVille());
            System.out.println("Nombres de Chambres : " + h.getNombre_chambres());
            System.out.println("Nombres de places : " + h.getNombre_places());
            System.out.println("Prix : " + h.getPrix());
            System.out.println("Distance Centre : "+ h.getDistanceCentre());
            System.out.println("Wifi : " + h.getWifi());
            System.out.println("Menage : " + h.getMenage());
            System.out.println("Fumeur : " + h.getFumeur());
            // ... afficher d'autres attributs selon votre structure de données
            System.out.println("--------------------");
        }
    }

    public void SuppHergement() throws SQLException, ClassNotFoundException {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        String choix;
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-- Quelle hebergement voulez vous supprimer --");
        System.out.println("-- entrer l'id de l'hetablissement --");
        choix=clavier.next();
        choix="'"+choix+"'";
        dataco.Data_Supp_Hebergement(choix);

    }
    public void ActuHergement() throws SQLException, ClassNotFoundException {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement

    }

    public void PromoH() throws SQLException, ClassNotFoundException {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        String choix1;
        String choix2;
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-- Sur hebergement voulez vous Ajouter une promotion --");
        System.out.println("-- entrer l'id de l'hetablissement --");
        choix1=clavier.next();
        choix1="'"+choix1+"'";
        System.out.println("-- quelle promo --");
        choix2=clavier.next();
        choix2="'"+choix1+"'";
        //dataco.Data_Promo_Hebergement(choix1, choix2);

    }

    public void AjoutHbergement() throws SQLException, ClassNotFoundException {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        Scanner clavier = new Scanner(System.in);
        int nombre_chambres = 0;
        int nombre_places=0;
        int prix=0;
        int distanceCentre=0;
        String Wifi=null;
        String Menage=null;
        String fumeur=null;


        System.out.print("nom de l'hotel: ");
        String nom = clavier.nextLine();

        System.out.print("ville : ");
        String ville = clavier.nextLine();


        do{
            System.out.print("nombre de chambre : ");
            nombre_chambres = Integer.parseInt(clavier.nextLine());
        }while((1>nombre_chambres)||(4<nombre_chambres));

        do{
            System.out.print("nombre de places : ");
            nombre_places = Integer.parseInt(clavier.nextLine());
        }while((2>nombre_places)||(8<nombre_places));

        do{
            System.out.print("prix : ");
            prix = Integer.parseInt(clavier.nextLine());
        }while((50>prix)||(10000<prix));

        do{
            System.out.print("distance du centre : ");
            distanceCentre = Integer.parseInt(clavier.nextLine());
        }while((50>distanceCentre)||(10000<distanceCentre));


        do{
            System.out.print("Wifi : ");
            Wifi=clavier.nextLine();
        }while((!Objects.equals(Wifi, "oui"))||(!Wifi.equals("non")));
        do{
            System.out.print("Menage : ");
            Menage=clavier.nextLine();
        }while((!Objects.equals(Menage, "oui"))||(!Menage.equals("non")));
        do{
            System.out.print("Fumeur : ");
            fumeur=clavier.nextLine();
        }while((!Objects.equals(fumeur, "oui"))||(!fumeur.equals("non")));


        int numClient=10;
        //dataco.Data_Creation_Herbergement();
                   /* for (Client client : COListe) {
                            if ((id.equals(client.getId())) && (mdp.equals(client.getMdp()))) {

                                Client ConnexionClient = new Client(client.getNom(), id, mdp, client.getNumero(), hebergementListe);
                                ConnexionClient.menu();
                                buff=1;
                            }
                        }*/

    }










    public void gererClient() throws SQLException, ClassNotFoundException {

        dataco.SQL_Data_Login(COListe);
        String choixH;
        Scanner clavier = new Scanner(System.in);
        do{


            System.out.println("\n======= Menu Admin ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Afficher la liste des Clients");
            System.out.println("2. Supprimer un client");
            System.out.println("3. Ajouter une reduction aux clients");
            System.out.println("4. Liste des Reservations");
            System.out.println("5. Supprimer une reservation");
            System.out.print("\nsaisir menu : ");
            choixH = clavier.next();


            switch (choixH) {
                case "0" -> System.out.println("Merci");
                case "1" -> afficherListeClient(COListe);   //gererHebrgement();
                case "2" -> SuppClient(COListe);
                case "3" -> PromoC();
                //case "4" ->;
                //case "5" ->;
                default -> {
                }
            }
        } while (!choixH.equals("0"));


    }

    public void afficherListeClient(ArrayList<Client> liste) {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        for (Client C : liste) {
            System.out.println("Nom d utilisateur : "+ C.getNom());
            System.out.println("Pseudo d utilisateur : " + C.getUtilisateur());
            System.out.println("Mdp du client  : " + C.getMdp());
            System.out.println("Num du client : " + C.getId());
            // ... afficher d'autres attributs selon votre structure de données
            System.out.println("--------------------");
        }
    }

    public void SuppClient(ArrayList<Client> liste) {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement

    }

    public void PromoC() throws SQLException, ClassNotFoundException {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        String choix1;
        String choix2;
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-- Sur hebergement voulez vous Ajouter une promotion --");
        System.out.println("-- entrer l'id de l'hetablissement --");
        choix1=clavier.next();
        choix1="'"+choix1+"'";
        System.out.println("-- quelle promo --");
        choix2=clavier.next();
        choix2="'"+choix1+"'";
        //dataco.Data_Promo_Client(choix1, choix2);

    }

}






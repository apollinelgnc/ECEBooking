package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Reservation;
import com.example.ecebooking.Models.DataCo;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

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


    /** METHODES */


    //SP menu de l admin
    public void menuAdmin() throws SQLException, ClassNotFoundException {
        String choix;
        Scanner clavier = new Scanner(System.in);
        do {

            dataco.SQL_Data_Admin(ADListe);

            System.out.println("\n======= Menu Admin ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Gerer hebergement");
            System.out.println("2. Gerer à la liste des clients");
            System.out.print("\nsaisir menu : ");
            choix = clavier.next();

            switch (choix) {                //choix de l option
                case "0" -> System.out.println("Merci0");
                case "1" -> gererHebrgement(); //gererHebrgement();
                case "2" -> gererClient();
                default -> {
                }
            }
        } while (!choix.equals("0"));       //blindage
    }

    //gere la partie des options sur les hebergment
    public void gererHebrgement() throws SQLException, ClassNotFoundException {

        String choixH;
        Scanner clavier = new Scanner(System.in);
        do{
            dataco.SQL_Data_Hebergements2(hebergementListe);
            System.out.println("\n======= Menu Admin ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Afficher la liste des hebergement");
            System.out.println("2. Supprimer un hebergement");
            System.out.println("3. Acutaliser un hebergement");                         //menu
            System.out.println("4. Ajouter une reduction a un hebergement");
            System.out.println("5. Ajouter un hebergement");
            System.out.print("\nsaisir menu : ");
            choixH = clavier.next();
            switch (choixH) {               //option disponible pour gere les hebergments
                case "0" -> System.out.println("Merci");
                case "1" -> afficherListeHebergements(hebergementListe);
                case "2" -> SuppHergement();
                case "3" -> ActuHergement();
                case "4" -> PromoH();
                case "5" -> AjoutHbergement();
                default -> {
                }
            }
        } while (!choixH.equals("0"));          //blindage
    }

    //SP pour l afficahge de toute la liste des hebergments de la bdd
    public void afficherListeHebergements(ArrayList<Hebergement> liste) {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        for (Hebergement h : liste) {
            System.out.println("Hébergement : "+ h.getIdhebergement());
            System.out.println("Nom : " + h.getNom_etablissement());
            System.out.println("Ville : " + h.getVille());
            System.out.println("Nombres de Chambres : " + h.getNombre_chambres());
            System.out.println("Nombres de places : " + h.getNombre_places());
            System.out.println("Prix : " + h.getPrix());                            //affichage des attribut de chacun des nos hebergments
            System.out.println("Distance Centre : "+ h.getDistanceCentre());
            System.out.println("Wifi : " + h.getWifi());
            System.out.println("Menage : " + h.getMenage());
            System.out.println("Fumeur : " + h.getFumeur());

            System.out.println("--------------------");
        }
    }


    //SP de suppresion d un hebergment
    public void SuppHergement() throws SQLException, ClassNotFoundException {
        String choix;
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-- Quelle hebergement voulez vous supprimer --");
        System.out.println("-- entrer l'id de l'hetablissement --");
        choix=clavier.next();           //demande de la suppresion de quelle hebergùent l admin veut il faire
        choix="'"+choix+"'";            //choix de l id de l hebergment a supp
        dataco.Data_Supp_Hebergement(choix);            //envoie de l id de l hebergment a supprimer pour creee la bonne requete sql

    }

    //SP de mise a jour des donne d un hebergment
    public void ActuHergement() throws SQLException, ClassNotFoundException {
        String nom_etablissement =null;
        String ville=null;
        int nombre_chambres= 0;
        int nombre_places= 0;
        int prix= 0;
        int distanceCentre= 0;          //intitalisation des nos variable
        String wifi=null;
        String menage=null;
        String  fumeur=null;
        String S2="";
        int buff=0;

        Scanner clavier = new Scanner(System.in);
        String choix;

        do {
            System.out.println(S2);
            System.out.println("\n======= Menu Update ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. nom etablissement : " + nom_etablissement);
            System.out.println("2. ville : " + ville);
            System.out.println("3. nb chambre : " + nombre_chambres);
            System.out.println("4. nb place : " + nombre_places);
            System.out.println("5. prix : " + prix);                            //liste des donne a mettre a jour avec les otpion possible de maj
            System.out.println("6. distance centre : " + distanceCentre);
            System.out.println("7. wifi : " + wifi);
            System.out.println("8. menage : " + menage);
            System.out.println("9. fumeur : " + fumeur);
            System.out.println("10. update");
            System.out.print("\nsaisir choix: ");
            choix = clavier.next();

            switch (choix) {            //choix de quelle otpion mettre a jour pour l hebergment selectionne par la suite
                //quitter
                case "0" -> System.out.println("Merci");

                // nom hebergement
                case "1" -> {
                    System.out.print("Veuillez saisir le nom : ");
                    nom_etablissement= clavier.next();          //saisit du nom a mettre a jour pour l hebergment selectionne par la suite
                    if(buff==0)
                    {
                        S2=S2+" `nom`='" + nom_etablissement +"'";          //creation de la string pour realiser la requete de maj apres
                        buff=1;
                    }
                    else{
                        S2=S2+", `nom`='" + nom_etablissement +"'";         //creation de la string pour realiser la requete de maj apres
                    }


                }

                // ville
                case "2" -> {
                    System.out.print("Veuillez saisir la ville : ");
                    ville= clavier.next();          //choix de la maj a faire
                    if(buff==0)
                    {
                        S2=S2+" `ville`= '" + ville +"'";   //creation de la string pour realiser la requete de maj apres
                        buff=1;
                    }
                    else{
                        S2=S2+", `ville`= '" + ville +"'";  //creation de la string pour realiser la requete de maj apres
                    }

                }
                // chambre
                case "3" -> {
                    System.out.print("Veuillez saisir le nombre de chambre : ");
                    nombre_chambres = clavier.nextInt();        //choix de la maj a faire
                    if(buff==0)
                    {
                        S2=S2+" `nbChambre`='" + nombre_chambres+ "'";  //creation de la string pour realiser la requete de maj apres
                        buff=1;
                    }
                    else{
                        S2=S2+", `nbChambre`='" + nombre_chambres+ "'"; //creation de la string pour realiser la requete de maj apres
                    }

                }
                // place
                case "4" -> {
                    System.out.print("Veuillez saisir le nombre de place : ");
                    nombre_places= clavier.nextInt();       //choix de la maj a faire
                    if(buff==0)
                    {
                        S2=S2+" `nbPlace`='" + nombre_places+ "'";  //creation de la string pour realiser la requete de maj apres
                        buff=1;
                    }
                    else{
                        S2=S2+", `nbPlace`='" + nombre_places+ "'"; //creation de la string pour realiser la requete de maj apres
                    }

                }
                // prix
                case "5" -> {
                    System.out.print("Veuillez saisir le prix : ");
                    prix= clavier.nextInt();            //choix de la maj a faire
                    if(buff==0)
                    {
                        S2=S2+" `prix`='" + prix+ "'";  //creation de la string pour realiser la requete de maj apres
                        buff=1;
                    }
                    else{
                        S2=S2+", `prix`='" + prix+ "'"; //creation de la string pour realiser la requete de maj apres
                    }

                }
                //distance
                case "6" -> {
                    System.out.print("Veuillez saisir la distance au centre : ");
                    distanceCentre= clavier.nextInt();          //choix de la maj a faire
                    if(buff==0)
                    {
                        S2=S2+" `distanceCentre`='" + distanceCentre+ "'";  //creation de la string pour realiser la requete de maj apres
                        buff=1;
                    }
                    else{
                        S2=S2+", `distanceCentre`='" + distanceCentre+ "'"; //creation de la string pour realiser la requete de maj apres
                    }

                }
                //option de wifi
                case "7" -> {
                    do{
                        System.out.print("Wifi : ");
                        wifi=clavier.nextLine();        //choix de la maj a faire
                    }while((!Objects.equals(wifi, "oui"))&&(!wifi.equals("non")));      //blindage

                    if(wifi.equals("oui"))      //creation de la string pour la requete en fonction de la reponse du blindage
                    {
                        if(buff==0)
                        {
                            S2=S2+"  `wifi`='1'";
                            buff=1;                     //creation de la string pour realiser la requete de maj apres
                        }
                        else{
                            S2=S2+",  `wifi`='1'";      //creation de la string pour realiser la requete de maj apres
                        }

                    }else{                      //creation de la string pour la requete en fonction de la reponse du blindage
                        if(buff==0)
                        {
                            S2=S2+" `wifi`='0'";        //creation de la string pour realiser la requete de maj apres
                            buff=1;
                        }
                        else{
                            S2=S2+", `wifi`='0'";       //creation de la string pour realiser la requete de maj apres
                        }

                    }
                }
                //option de menage
                case "8" -> {
                    do{
                        System.out.print("Menage : ");
                        menage=clavier.nextLine();      //choix de la maj a faire
                    }while((!Objects.equals(menage, "oui"))&&(!menage.equals("non")));      //blindage

                    if(menage.equals("oui"))        //creation de la string pour la requete en fonction de la reponse du blindage
                    {
                        if(buff==0)
                        {
                            S2=S2+"  `menage`='1'"; //creation de la string pour realiser la requete de maj apres
                            buff=1;
                        }
                        else{
                            S2=S2+",  `menage`='1'";        //creation de la string pour realiser la requete de maj apres
                        }
                    }else{          //creation de la string pour la requete en fonction de la reponse du blindage
                        if(buff==0)
                        {
                            S2=S2+" `menage`='0'";      //creation de la string pour realiser la requete de maj apres
                            buff=1;
                        }
                        else{
                            S2=S2+", `menage`='0'";     //creation de la string pour realiser la requete de maj apres
                        }

                    }
                }
                //option de fumeur
                case "9" -> {
                    do{
                        System.out.print("Fumeur : ");
                        fumeur=clavier.nextLine();      //choix de la maj a faire
                    }while((!Objects.equals(fumeur, "oui"))&&(!fumeur.equals("non")));      //blindage

                    if(fumeur.equals("oui"))        //creation de la string pour la requete en fonction de la reponse du blindage
                    {
                        if(buff==0)
                        {
                            S2=S2+"  `fumeur`='1'";     //creation de la string pour realiser la requete de maj apres
                            buff=1;
                        }
                        else{
                            S2=S2+",  `fumeur`='1'";        //creation de la string pour realiser la requete de maj apres
                        }

                    }else{          //creation de la string pour la requete en fonction de la reponse du blindage
                        if(buff==0)
                        {
                            S2=S2+" `fumeur`='0'";      //creation de la string pour realiser la requete de maj apres
                            buff=1;
                        }
                        else{
                            S2=S2+", `fumeur`='0'";     //creation de la string pour realiser la requete de maj apres
                        }

                    }
                }
                //validation des maj a faire
                case "10" -> System.out.println("option de maj valide");
                default -> {
                }
            }

        } while (!choix.equals("10"));              //blindage
        String S1="UPDATE `etablissement` SET ";        //debut la requete pour faire la maj
        String S3=" WHERE `id`=";

        System.out.println("-- entrer l'id du client --");      //choix de l hebergment a mettre a jour
        choix=clavier.next();           //saisit de l id
        choix="'"+choix+"'";
        S1=S1+S2+S3+choix;          //creation de la requete pour realiser la maj en fonction de l id donne
        //System.out.println(S1);
        dataco.Data_Actu_Hebergement(S1);           //envoie de la requete ou SP qui executera la requete dans la bdd


    }

    //SP de saisit de la promotion a mettre a jour pour un herbegement en particulier
    public void PromoH() throws SQLException, ClassNotFoundException {
        String choix1;
        String choix2;
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-- Sur hebergement voulez vous Ajouter une promotion --");
        System.out.println("-- entrer l'id de l'hetablissement --");
        choix1=clavier.next();      //saisit de lid de l hebergment ou on doit modifier la promo
        System.out.println("-- quelle promo --");
        choix2=clavier.next();      //modification de la promo
        dataco.Data_Promo_Hebergement(choix1, choix2);  //envoie d'une partie avec la promo et l id de l hebergment a modif au SP qui executera la requete dans la bdd

    }


    //SP de saisit des attributs pour ajouter un herbegement
    public void AjoutHbergement() throws SQLException, ClassNotFoundException {
        Scanner clavier = new Scanner(System.in);
        int nombre_chambres;
        int nombre_places;
        int prix;
        int distanceCentre;
        String Snombre_chambres;
        String Snombre_places;          //intialisation des donne a ajoute pour un hebergment
        String Sprix;
        String SdistanceCentre;
        String Wifi;
        String Menage;
        String fumeur;

        //saisit du nom de l hotel pour l hebergment a cree
        System.out.print("nom de l'hotel: ");
        String nom = clavier.nextLine();

        //saisit du nom de la ville pour l hebergment a cree
        System.out.print("ville : ");
        String ville = clavier.nextLine();

        //saisit du nombre de chambre de l hotel pour l hebergment a cree
        do{
            System.out.print("nombre de chambre : ");
            nombre_chambres = Integer.parseInt(clavier.nextLine());
        }while((1>nombre_chambres)||(4<nombre_chambres));   //blindage
        Snombre_chambres= String.valueOf(nombre_chambres); //passage du type int a string

        //saisit du nombre de place de l hotel pour l hebergment a cree
        do{
            System.out.print("nombre de places : ");
            nombre_places = Integer.parseInt(clavier.nextLine());
        }while((2>nombre_places)||(8<nombre_places));   //blindage
        Snombre_places=String.valueOf(nombre_places);       //passage du type int a string

        //saisit du prix de l hotel pour l hebergment a cree
        do{
            System.out.print("prix : ");
            prix = Integer.parseInt(clavier.nextLine());
        }while((50>prix)||(10000<prix));    //blindage
        Sprix=String.valueOf(prix);         //passage du type int a string

        //saisit de la disnatce au centre de l hotel pour l hebergment a cree
        do{
            System.out.print("distance du centre : ");
            distanceCentre = Integer.parseInt(clavier.nextLine());
        }while((50>distanceCentre)||(10000<distanceCentre));        //blindage
        SdistanceCentre=String.valueOf(distanceCentre);     //passage du type int a string

        //saisit de l option wifi de l hotel pour l hebergment a cree
        do{
            System.out.print("Wifi : ");
            Wifi=clavier.nextLine();
        }while((!Objects.equals(Wifi, "oui"))&&(!Wifi.equals("non")));  //blindage
        if(Wifi.equals("oui"))
        {
            Wifi="1";           //test pour savoir quelle donne envoye pour la requete 1=oui 0=non
        }else{
            Wifi="0";
        }

        //saisit de l option menage de l hotel pour l hebergment a cree
        do{
            System.out.print("Menage : ");
            Menage=clavier.nextLine();
        }while((!Objects.equals(Menage, "oui"))&&(!Menage.equals("non")));  //blindage
        if(Menage.equals("oui"))
        {
            Menage="1";             //test pour savoir quelle donne envoye pour la requete 1=oui 0=non
        }else{
            Menage="0";
        }

        //saisit de l option fumeur de l hotel pour l hebergment a cree
        do{
            System.out.print("Fumeur : ");
            fumeur=clavier.nextLine();
        }while((!Objects.equals(fumeur, "oui"))&&(!fumeur.equals("non")));  //blindage
        if(fumeur.equals("oui"))
        {
            fumeur="1";     //test pour savoir quelle donne envoye pour la requete 1=oui 0=non
        }else{
            fumeur="0";
        }

        int num=37;
        dataco.Data_Ajout_Hebergement(nom, ville, Snombre_chambres, Snombre_places, Sprix, SdistanceCentre, Wifi, Menage, fumeur, num);

    }

    /** Client */

    //menu pour gere les options de clients
    public void gererClient() throws SQLException, ClassNotFoundException {


        String choixH;
        Scanner clavier = new Scanner(System.in);
        do{

            dataco.SQL_Data_Login(COListe);
            System.out.println("\n======= Menu Admin ======\n");
            System.out.println("0. Quitter");
            System.out.println("1. Afficher la liste des Clients");
            System.out.println("2. Supprimer un client");                   //menu des options possible
            System.out.println("3. Ajouter une reduction aux clients");
            System.out.println("4. Liste des Reservations");
            System.out.println("5. Supprimer une reservation");
            System.out.print("\nsaisir menu : ");
            choixH = clavier.next();


            switch (choixH) {       //saisit des option possible
                case "0" -> System.out.println("Merci");            //quitter
                case "1" -> afficherListeClient(COListe);           //affichage de la liste des clients
                case "2" -> SuppClient();           //suppresion d un client
                case "3" -> PromoC();                           //ajoute d une nouvelle promo a un client
                case "4" -> AfficherReservation();          //affichage de la liste des resa
                case "5" -> SuppReza();             //suppresion d un resa effecute
                default -> {
                }
            }
        } while (!choixH.equals("0"));


    }

    //SP affichage de la liste des clients
    public void afficherListeClient(ArrayList<Client> liste) {
        // Parcourir la liste d'client et afficher les informations de chaque client
        for (Client C : liste) {
            System.out.println("Nom d utilisateur : "+ C.getNom());
            System.out.println("Pseudo d utilisateur : " + C.getUtilisateur());
            System.out.println("Mdp du client  : " + C.getMdp());           //afficaheg de chaque attribut de chauqe client
            System.out.println("ID du client : " + C.getId());

            System.out.println("--------------------");
        }
    }

    //SP de suppresion d un client dans la bdd
    public void SuppClient() throws SQLException, ClassNotFoundException {

        String choix;
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-- Quelle client voulez vous supprimer --");
        System.out.println("-- entrer l'id du client --");
        choix=clavier.next();               //saisit de l id du client a supprimer
        choix="'"+choix+"'";                //creation de la string pour realiser la requete de suppresion
        dataco.Data_Supp_Client(choix);         //envoie de la string de l id de l hebergment pour faire la requete de suppresion

    }

    //SP de maj de la promotion d un client dans la bdd
    public void PromoC() throws SQLException, ClassNotFoundException {

        String choix1;
        String choix2;
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-- Pour client voulez vous Ajouter une promotion --");
        System.out.println("-- entrer l'id du client --");
        choix1=clavier.next();          //saisit de l id du client sur qui il faut mettre a jour la promotion qu 'il a
        System.out.println("-- quelle promo --");
        choix2=clavier.next();          //nouvelle promotion possible pour le client
        dataco.Data_Promo_Client(choix1, choix2);           //envoie des strinf de l id client et la promo pour realiser la requete sql et update la bdd

    }

    //SP d affichage de la liste des reservation faites
    public void AfficherReservation() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> ListeReservation = dataco.SQL_Data_Reservation();

        for(Reservation resa : ListeReservation)     //parcours de la liste de reservation
        {
            resa.afficher();         //affichage de tout les attribut de resveration
        }
    }

    //SP de suppresion d une resa dnas la liste des ras realiser
    public void SuppReza() throws SQLException, ClassNotFoundException {

        String choix;
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-- Quelle resa voulez vous supprimer --");
        System.out.println("-- entrer l'id de la resa --");
        choix=clavier.next();       //saiit de l id de la resa a supprimer
        choix="'"+choix+"'";
        dataco.Data_Supp_Reza(choix);       //envoie de la string de l id de la resa pour faire la requete de suppresion

    }


    /** GETTERS / SETTERS */

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






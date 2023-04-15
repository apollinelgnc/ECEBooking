package com.example.ecebooking.Models;

import com.example.ecebooking.Controllers.Admin.Admin;
import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Controllers.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataCo {

    ArrayList<Hebergement>  hebergements = new ArrayList<>();

    //SP recuperation de la liste de nos client dans notre bdd
    public void SQL_Data_Login(ArrayList<Client> Client) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        c1.ajouterTable("client");      //ouverture de la table client dans la bdd
        c1.ajouterRequete("SELECT * FROM `client` ");           //requete envoyé à la bdd
        for(int i=0;i<c1.requetes.size();i++)
        {                       //parcours de la taille de la requete

            for(int j=0;j<c1.remplirChampsRequete(c1.requetes.get(i)).size();j++)
            {
                //passage des donnes de la bdd d une forme string que l on separe pour remplir les attribut de notre classe client
                String str = c1.remplirChampsRequete(c1.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                String nom = words[0];
                String utilisateur = words[1];                      //recuperation des donnes voulu de la bdd
                String mdp = words[2];
                int id = Integer.parseInt(words[3]);
                double reduction = Double.parseDouble(words[4]);

                Client C = new Client( nom, utilisateur, mdp, id, reduction);       //creation d un nouveau client avec les donnes extrait de la bdd

                Client.add(C);          //ajout de notre nouveau client dans la liste de nos client
            }
        }
    }

    //SP recuperation de la liste de nos admins dans notre bdd
    public void SQL_Data_Admin(ArrayList<Admin> Admin) throws SQLException, ClassNotFoundException {
        DataBaseConnection c3 = new DataBaseConnection("bdd_projets6", "root", "0802");

        c3.ajouterTable("admin");       //ouverture de la table client dans la bdd
        c3.ajouterRequete("SELECT * FROM `admin` ");         //requete envoyé à la bdd
        for(int i=0;i<c3.requetes.size();i++)
        {                       //parcours de la taille de la requete

            for(int j=0;j<c3.remplirChampsRequete(c3.requetes.get(i)).size();j++)
            {
                //passage des donnes de la bdd d une forme string que l on separe pour remplir les attribut de notre classe admin
                String str = c3.remplirChampsRequete(c3.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                String nom = words[0];
                String utilisateur = words[1];          //recuperation des donnes voulu de la bdd
                String mdp = words[2];
                int id = Integer.parseInt(words[3]);

                Admin C = new Admin( nom, utilisateur, mdp, id); //creation d un nouvel admin avec les donnes extrait de la bdd
                Admin.add(C);           //ajout de notre nouvel admin dans la liste de nos admin
            }
        }


    }

    //SP Creation d un nouvel urtilisateur dans la table client de la bdd SIGN IN
    public void Data_Creation_Login(String nom, String id, String mdp, int num) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        int count = 0;
        ArrayList<Client> ListeClient = new ArrayList<>();
        SQL_Data_Login(ListeClient);
        for(Client resa : ListeClient)
        {
            if(resa.getId() > count)
            {
                count = resa.getId();
            }
        }
        count++;

        String Snum= String.valueOf(num);           //passage du type int au type string
        String S1 ="INSERT INTO `client` (`nom`, `utilisateur`, `mdp`, `id`, `reduction`) VALUES ('";       //debut d une requete de creation
        String S2="'";
        String S3=", ";             //string pour cree la bonne requete voulu
        String S4=", '0.9')";
        S1=S1+nom+S2+S3+S2+id+S2+S3+S2+mdp+S2+S3+S2+count+S2+S4;     //creation de la requete de creation d un nouvel utilisateur
        c1.ajouterRequete(S1);       //requete envoyé à la bdd
        c1.executeUpdate(c1.requetes.get(0));               //excution de la requete dans la bdd
    }

    //SP Suppresion d un client dans la table client de la bdd ainsi que des ses reservation
    public void Data_Supp_Client(String idClient) throws SQLException, ClassNotFoundException {
        // Supprimer les resa du client
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S1 ="DELETE FROM `reservation` WHERE `idClient`=";
        S1=S1+idClient;      //string pour cree la bonne requete voulu
        //System.out.println(S1);
        c1.ajouterRequete(S1);              //requete envoyé à la bdd
        c1.executeUpdate(c1.requetes.get(0));            //excution de la requete dans la bdd

        // SUpprimer le client
        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S2 ="DELETE FROM `client` WHERE `id`=";
        S2=S2+idClient;              //string pour cree la bonne requete voulu
        //System.out.println(S2);
        c2.ajouterRequete(S2);          //requete envoyé à la bdd
        c2.executeUpdate(c2.requetes.get(0));            //excution de la requete dans la bdd

    }

    //SP Modification de la promo qu certain client peut avoir lors des ses reservation
    public void Data_Promo_Client(String choix1,String choix2) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S1 ="UPDATE `client` SET `reduction`='";     //debut d une requete de mise a jour
        String S2="'";
        String S4=" WHERE `id`='";          //string pour cree la bonne requete voulu
        S1=S1+choix2+S2+S4+choix1+S2;       //string pour cree la bonne requete voulu
        //System.out.println(S1);
        c1.ajouterRequete(S1);       //requete envoyé à la bdd
        c1.executeUpdate(c1.requetes.get(0));
    }


    //SP Recuperation de la liste de tout les hebergement de notre bdd
    public void SQL_Data_Hebergements2(ArrayList<Hebergement> hebergements) throws SQLException, ClassNotFoundException {

        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "0802");
        c2.ajouterTable("etablissement");        //ouverture de la table etablissement dans la bdd
        //recherche de tous les etablisemeent dans la base de donnée
        c2.ajouterRequete("SELECT * FROM `etablissement` ");         //requete envoyé à la bdd
        for(int i=0;i<c2.requetes.size();i++)
        {               //parcours de la taille de la requete

            for(int j=0;j<c2.remplirChampsRequete(c2.requetes.get(i)).size();j++)
            {
                //passage des donnes de la bdd d une forme string que l on separe pour remplir les attribut de notre classe hebergement
                String str = c2.remplirChampsRequete(c2.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");

                String nom_etablissement = words[0];
                String ville = words[1];
                int nombre_chambres = Integer.parseInt(words[2]);
                int nombre_places = Integer.parseInt(words[3]);
                int prix = Integer.parseInt(words[4]);
                int distanceCentre = Integer.parseInt(words[5]);
                int wifi = Integer.parseInt(words[6]);          //recuperation des donnes voulu de la bdd
                int menage = Integer.parseInt(words[7]);
                int fumeur = Integer.parseInt(words[8]);
                int idhebergement = Integer.parseInt(words[9]);
                double promo = Double.parseDouble((words[10]));

                //creation d un nouvel hebergement avec les donnes extrait de la bdd
                Hebergement h = new Hebergement(nom_etablissement, ville, nombre_chambres, nombre_places, prix, distanceCentre, wifi, menage, fumeur, idhebergement, promo);
                //ajout de notre nouvel hebergement dans la liste de nos hebergement
                hebergements.add(h);
            }
        }

    }


    //SP Recuperation de la liste de tout les hebergement de notre bdd  en fonction du filtre envoye par la requete
    public ArrayList<Hebergement> SQL_Data_Hebergements(String request) throws SQLException, ClassNotFoundException {


        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "0802");
        c2.ajouterTable("etablissement");       //ouverture de la table etablissement dans la bdd
        c2.ajouterRequete(request);          //requete envoyé à la bdd

        for(int i=0;i<c2.requetes.size();i++)
        {   //parcours de la taille de la requete

            for(int j=0;j<c2.remplirChampsRequete(c2.requetes.get(i)).size();j++)
            {
                //passage des donnes de la bdd d une forme string que l on separe pour remplir les attribut de notre classe hebergement
                String str = c2.remplirChampsRequete(c2.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");

                String nom_etablissement = words[0];
                String ville = words[1];
                int nombre_chambres = Integer.parseInt(words[2]);
                int nombre_places = Integer.parseInt(words[3]);
                int prix = Integer.parseInt(words[4]);
                int distanceCentre = Integer.parseInt(words[5]);        //recuperation des donnes voulu de la bdd
                int wifi = Integer.parseInt(words[6]);
                int menage = Integer.parseInt(words[7]);
                int fumeur = Integer.parseInt(words[8]);
                int idhebergement = Integer.parseInt(words[9]);
                double promo = Double.parseDouble(words[10]);

                //creation d un nouvel hebergement avec les donnes extrait de la bdd
                Hebergement h = new Hebergement(nom_etablissement, ville, nombre_chambres, nombre_places, prix, distanceCentre, wifi, menage, fumeur, idhebergement, promo);
                //ajout de notre nouvel hebergement dans la liste de nos hebergement
                hebergements.add(h);
            }
        }

        //retourne la liste des hebergement en fonction du filtre demande
        return hebergements;
    }


    //SP Suppresion d un hebergment dans la table etablissement de la bdd ainsi que des ces reservation
    public void Data_Supp_Hebergement(String idHebergement) throws SQLException, ClassNotFoundException {
        //suppresion des reservation lie a l hebergement
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S1 ="DELETE FROM `reservation` WHERE `idHebergement`=";
        S1=S1+idHebergement;            //creation de la requete en concatenant des chaines de caratere
        System.out.println(S1);
        c1.ajouterRequete(S1);      //requete envoyé à la bdd
        c1.executeUpdate(c1.requetes.get(0));       //execution de la requete envoyé à la bdd

        //suppresion de l etablissement
        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S2 ="DELETE FROM `etablissement` WHERE `id`=";
        S2=S2+idHebergement;            //creation de la requete en concatenant des chaines de caratere
        System.out.println(S2);
        c2.ajouterRequete(S2);          //requete envoyé à la bdd
        c2.executeUpdate(c2.requetes.get(0));       //execution de la requete envoyé à la bdd
    }

    //SP Mise a jour d un hebergment dans la table etablissement de la bdd
    public void Data_Actu_Hebergement(String S1) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        //System.out.println(S1);
        c1.ajouterRequete(S1);           //requete envoyé à la bdd
        c1.executeUpdate(c1.requetes.get(0));       //execution de la requete envoyé à la bdd
    }

    //SP ajout d un hebergment dans la table etablissement de la bdd
    public void Data_Ajout_Hebergement(String nom,String ville,String Snombre_chambres,String Snombre_places,String Sprix,String SdistanceCentre,String Wifi,String Menage,String fumeur,int num) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        // Cherche l'id suivant dispo
        int count = 0;
        ArrayList<Hebergement> ListeHebergement = new ArrayList<>();
        SQL_Data_Hebergements2(ListeHebergement);
        for(Hebergement hebe : ListeHebergement)
        {
            if(hebe.getIdhebergement() > count)
            {
                count = hebe.getIdhebergement();
            }
        }
        count++;
        //string de debut de requete pour cree un nouvel hebergement
        String S1 ="INSERT INTO `etablissement`(`nom`, `ville`, `nbChambre`, `nbPlace`, `prix`, `distanceCentre`, `wifi`, `menage`, `fumeur`, `id`, `Promo`) VALUES ('";
        String S2="'";
        String S3=", ";             //string pour cree la bonne requete voulu
        String S4=", '1')";           //string pour cree la bonne requete voulu

        //string ou 'lon concatene tout les attribut a ajouter avec les condition demander pour creer la requete
        S1=S1+nom+S2+S3+S2+ville+S2+S3+S2+Snombre_chambres+S2+S3+S2+ Snombre_places+S2+S3+S2+ Sprix+S2+S3+S2+ SdistanceCentre+S2+S3+S2+ Wifi+S2+S3+S2+ Menage+S2+S3+S2+ fumeur+S2+S3+S2+ count+S2+S4;
        //System.out.println(S1);
        c1.ajouterRequete(S1);           //requete envoyé à la bdd
        c1.executeUpdate(c1.requetes.get(0));
    }

    //SP mise a jour de la promotion d un hebergment
    public void Data_Promo_Hebergement(String choix1,String choix2) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        //string de debut de requete pour mettre a jour la promotion sur un hebergement
        String S1 ="UPDATE `etablissement` SET `Promo`='";
        String S2="'";          //string pour cree la bonne requete voulu
        String S4=" WHERE `id`='";          //string pour cree la bonne requete voulu
        //string ou l'on concatene tout les attribut a ajouter avec les condition demander pour creer la requete
        S1=S1+choix2+S2+S4+choix1+S2;
        //System.out.println(S1);
        c1.ajouterRequete(S1);       //requete envoyé à la bdd
        c1.executeUpdate(c1.requetes.get(0));
    }


    //SP de recuperation de la liste de nos Reservation
    public ArrayList<Reservation> SQL_Data_Reservation() throws SQLException, ClassNotFoundException {
        DataBaseConnection c4 = new DataBaseConnection("bdd_projets6", "root", "0802");

        c4.ajouterTable("reservation");     //ouverture de la table reservation dans la bdd
        c4.ajouterRequete("SELECT * FROM `reservation`");        //requete envoyé à la bdd
        ArrayList<Reservation> listeReservation = new ArrayList<>();

        for(int i=0;i<c4.requetes.size();i++)
        {
            //parcours de la taille de la requete
            for(int j=0;j<c4.remplirChampsRequete(c4.requetes.get(i)).size();j++)
            {
                //passage des donnes de la bdd d une forme string que l on separe pour remplir les attribut de notre classe reservation
                String str = c4.remplirChampsRequete(c4.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                int idHebegement = Integer.parseInt(words[0]);
                int idClient = Integer.parseInt(words[1]);
                int debutAnnee = Integer.parseInt(words[2]);
                int debutMois = Integer.parseInt(words[3]);
                int debutJour = Integer.parseInt(words[4]);         //recuperation des donnes voulu de la bdd
                int finAnnee = Integer.parseInt(words[5]);
                int finMois = Integer.parseInt(words[6]);
                int finJour = Integer.parseInt(words[7]);
                int prix = Integer.parseInt(words[8]);
                int id = Integer.parseInt(words[9]);

                LocalDate debut = LocalDate.of(debutAnnee,debutMois,debutJour);      //recuperation des dates lu dans la bdd
                LocalDate fin = LocalDate.of(finAnnee,finMois,finJour);


                //creation d une nouvelle reservation avec les donnes extrait de la bdd
                Reservation C = new Reservation( idHebegement, idClient, debut, fin, prix, id);
                //ajout de notre nouvel reservation lu dans la liste de nos resa
                listeReservation.add(C);
            }
        }
        //retourne la liste des reservations lu dans la bdd
        return listeReservation;
    }

    //SP de creation d une nouvelle reservation effectue
    public void Data_Creation_Reservation(Reservation nouveau) throws SQLException, ClassNotFoundException {
        DataBaseConnection c5 = new DataBaseConnection("bdd_projets6", "root", "0802");

        // Cherche l'id suivant dispo
        int count = 0;
        ArrayList<Reservation> ListeReservation = SQL_Data_Reservation();
        for(Reservation resa : ListeReservation)
        {
            if(resa.getId() > count)
            {
                count = resa.getId();               //augmentation du compteur de  l idd
            }
        }
        count++;

        //debut de la creation de la requete pour cree une nouvelle resa dans la table de reservation
        String S1 ="INSERT INTO `reservation` (`idHebergement`, `idClient`, `debutAnnee`, `debutMois`, `debutJour`, `finAnnee`, `finMois`, `finJour`, `prix`, `id`) VALUES ('";
        String S2="'";
        String S3=", ";         //string cree pour realiser la requete
        String S4="')";
        //concatenation de toutes les string pour cree la requete voulu et cree une reservation dans la bdd en sql
        S1=S1+nouveau.getId_hebergement()+S2+S3+S2+nouveau.getId_client()+S2+S3+S2+nouveau.getDebut().getYear()+S2+S3+S2+nouveau.getDebut().getMonthValue()+S2+S3+S2+nouveau.getDebut().getDayOfMonth()+S2+S3+S2+nouveau.getFin().getYear()+S2+S3+S2+nouveau.getFin().getMonthValue()+S2+S3+S2+nouveau.getFin().getDayOfMonth()+S2+S3+S2+nouveau.getPrix()+S2+S3+S2+count+S4;
        //System.out.println(S1);
        c5.ajouterRequete(S1);           //requete envoyé à la bdd
        c5.executeUpdate(c5.requetes.get(0));       //execution de la requete
    }

    //SP de suppresion d une reservation de notre bdd
    public void Data_Supp_Reza(String nom) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S1 ="DELETE FROM `reservation` WHERE `id`=";         //creation de la requete de suppresion
        S1=S1+nom;
        //System.out.println(S1);
        c1.ajouterRequete(S1);           //requete envoyé à la bdd
        c1.executeUpdate(c1.requetes.get(0));       //executoin de la requete envoye
    }
}
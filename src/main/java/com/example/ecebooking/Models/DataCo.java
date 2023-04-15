package com.example.ecebooking.Models;

import com.example.ecebooking.Controllers.Admin.Admin;
import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Controllers.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataCo {

    public void SQL_Data_Login(ArrayList<Client> Client) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        c1.ajouterTable("client");
        c1.ajouterRequete("SELECT * FROM `client` ");
        for (int i = 0; i < c1.requetes.size(); i++) {

            for (int j = 0; j < c1.remplirChampsRequete(c1.requetes.get(i)).size(); j++) {
                //passage de la bdd sous la forme d une liste d'hebergment
                String str = c1.remplirChampsRequete(c1.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                String nom = words[0];
                String utilisateur = words[1];
                String mdp = words[2];
                int id = Integer.parseInt(words[3]);
                double reduction = Double.parseDouble(words[4]);
                Client C = new Client(nom, utilisateur, mdp, id, reduction);
                Client.add(C);
            }
        }
    }

    public void SQL_Data_Admin(ArrayList<Admin> Admin) throws SQLException, ClassNotFoundException {
        DataBaseConnection c3 = new DataBaseConnection("bdd_projets6", "root", "0802");
        c3.ajouterTable("admin");
        c3.ajouterRequete("SELECT * FROM `admin` ");
        for (int i = 0; i < c3.requetes.size(); i++) {

            for (int j = 0; j < c3.remplirChampsRequete(c3.requetes.get(i)).size(); j++) {
                //passage de la bdd sous la forme d une liste d'hebergment
                String str = c3.remplirChampsRequete(c3.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                String nom = words[0];
                String utilisateur = words[1];
                String mdp = words[2];
                int id = Integer.parseInt(words[3]);
                Admin C = new Admin(nom, utilisateur, mdp, id);
                Admin.add(C);
            }
        }
    }

    public void Data_Creation_Login(String nom, String id, String mdp, int num) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");

        String Snum= String.valueOf(num);
        String S1 ="INSERT INTO `client` (`nom`, `utilisateur`, `mdp`, `id`, `reduction`) VALUES ('";
        String S2="'";
        String S3=", ";
        String S4=", '0.9')";
        S1=S1+nom+S2+S3+S2+id+S2+S3+S2+mdp+S2+S3+S2+Snum+S2+S4;
        c1.ajouterRequete(S1);
        c1.executeUpdate(c1.requetes.get(0));
    }


    public void SQL_Data_Hebergements2(ArrayList<Hebergement> hebergements) throws SQLException, ClassNotFoundException {

        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "0802");
        c2.ajouterTable("etablissement");
        //recherche de tous les etablisemeent dans la base de donnée
        c2.ajouterRequete("SELECT * FROM `etablissement` ");
        for (int i = 0; i < c2.requetes.size(); i++) {

            for (int j = 0; j < c2.remplirChampsRequete(c2.requetes.get(i)).size(); j++) {
                //passage de la bdd sous la forme d une liste d'hebergment
                String str = c2.remplirChampsRequete(c2.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                String nom_etablissement = words[0];
                String ville = words[1];
                int nombre_chambres = Integer.parseInt(words[2]);
                int nombre_places = Integer.parseInt(words[3]);
                int prix = Integer.parseInt(words[4]);
                int distanceCentre = Integer.parseInt(words[5]);
                int wifi = Integer.parseInt(words[6]);
                int menage = Integer.parseInt(words[7]);
                int fumeur = Integer.parseInt(words[8]);
                int idhebergement = Integer.parseInt(words[9]);
                Hebergement h = new Hebergement(nom_etablissement, ville, nombre_chambres, nombre_places, prix, distanceCentre, wifi, menage, fumeur, idhebergement);
                hebergements.add(h);
            }
        }
    }

    public ArrayList<Hebergement> SQL_Data_Hebergements(String request) throws SQLException, ClassNotFoundException {
        ArrayList<Hebergement> hebergements = new ArrayList<>();
        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "0802");
        c2.ajouterTable("etablissement");
        c2.ajouterRequete(request);
        for (int i = 0; i < c2.requetes.size(); i++) {
            for (int j = 0; j < c2.remplirChampsRequete(c2.requetes.get(i)).size(); j++) {
                //passage de la bdd sous la forme d une liste d'hebergment
                String str = c2.remplirChampsRequete(c2.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
               /* for (String word : words) {
                    System.out.println(word);
                }*/

                String nom_etablissement = words[0];
                String ville = words[1];
                int nombre_chambres = Integer.parseInt(words[2]);
                int nombre_places = Integer.parseInt(words[3]);
                int prix = Integer.parseInt(words[4]);
                int distanceCentre = Integer.parseInt(words[5]);
                int wifi = Integer.parseInt(words[6]);
                int menage = Integer.parseInt(words[7]);
                int fumeur = Integer.parseInt(words[8]);
                int idhebergement = Integer.parseInt(words[9]);
                Hebergement h = new Hebergement(nom_etablissement, ville, nombre_chambres, nombre_places, prix, distanceCentre, wifi, menage, fumeur, idhebergement);
                hebergements.add(h);
            }
        }
        return hebergements;
    }

    public void Data_Supp_Hebergement(String nom) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S1 = "DELETE FROM `etablissement` WHERE `id`=";
        S1 = S1 + nom;
        System.out.println(S1);
        c1.ajouterRequete(S1);
        c1.executeUpdate(c1.requetes.get(0));
    }

    public void Data_Actu_Hebergement(String nom) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S1 = "DELETE FROM `etablissement` WHERE `nom`=";
        S1 = S1 + nom;
        c1.ajouterRequete(S1);
        c1.executeUpdate(c1.requetes.get(0));
    }

    public void Data_Ajout_Hebergement(String nom) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "0802");
        String S1 = "INSERT INTO `etablissement`(`nom`, `ville`, `nbChambre`, `nbPlace`, `prix`, `distanceCentre`, `wifi`, `menage`, `fumeur`, `id`, `Promo`) VALUES ('";
        String S2 = "'";
        String S3 = ", ";
        String S4 = "')";
        // S1=S1+    +S2+S3+S2+  +S2+S3+S2+   +S2+S3+S2+    +S4;
        c1.ajouterRequete(S1);
        c1.executeUpdate(c1.requetes.get(0));
    }

    public ArrayList<Reservation> SQL_Data_Reservation() throws SQLException, ClassNotFoundException {
        DataBaseConnection c4 = new DataBaseConnection("bdd_projets6", "root", "0802");
        c4.ajouterTable("reservation");
        //c4.ajouterRequete("SELECT * FROM `reservation` WHERE idHebergement = " + id);
        c4.ajouterRequete("SELECT * FROM `reservation`");
        ArrayList<Reservation> listeReservation = new ArrayList<>();

        for (int i = 0; i < c4.requetes.size(); i++) {
            for (int j = 0; j < c4.remplirChampsRequete(c4.requetes.get(i)).size(); j++) {
                //passage de la bdd sous la forme d une liste de reservation
                String str = c4.remplirChampsRequete(c4.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                int idHebegement = Integer.parseInt(words[0]);
                int idClient = Integer.parseInt(words[1]);
                int debutAnnee = Integer.parseInt(words[2]);
                int debutMois = Integer.parseInt(words[3]);
                int debutJour = Integer.parseInt(words[4]);
                int finAnnee = Integer.parseInt(words[5]);
                int finMois = Integer.parseInt(words[6]);
                int finJour = Integer.parseInt(words[7]);
                int prix = Integer.parseInt(words[8]);
                int id = Integer.parseInt(words[9]);
                LocalDate debut = LocalDate.of(debutAnnee, debutMois, debutJour);
                LocalDate fin = LocalDate.of(finAnnee, finMois, finJour);
                Reservation C = new Reservation(idHebegement, idClient, debut, fin, prix, id);
                listeReservation.add(C);
            }
        }
        return listeReservation;
    }

    public void Data_Creation_Reservation(Reservation nouveau) throws SQLException, ClassNotFoundException {
        DataBaseConnection c5 = new DataBaseConnection("bdd_projets6", "root", "0802");

        // Cherche l'id suivant dispo
        int count = 0;
        ArrayList<Reservation> ListeReservation = SQL_Data_Reservation();
        for(Reservation resa : ListeReservation)
        {
            if(resa.getId() > count)
            {
                count = resa.getId();
            }
        }
        count++;

        //String S1 ="INSERT INTO `client` (`nom`, `utilisateur`, `mdp`, `id`) VALUES ('";
        String S1 ="INSERT INTO `reservation` (`idHebergement`, `idClient`, `debutAnnee`, `debutMois`, `debutJour`, `finAnnee`, `finMois`, `finJour`, `prix`, `id`) VALUES ('";
        String S2="'";
        String S3=", ";
        String S4="')";
        S1=S1+nouveau.getId_hebergement()+S2+S3+S2+nouveau.getId_client()+S2+S3+S2+nouveau.getDebut().getYear()+S2+S3+S2+nouveau.getDebut().getMonthValue()+S2+S3+S2+nouveau.getDebut().getDayOfMonth()+S2+S3+S2+nouveau.getFin().getYear()+S2+S3+S2+nouveau.getFin().getMonthValue()+S2+S3+S2+nouveau.getFin().getDayOfMonth()+S2+S3+S2+nouveau.getPrix()+S2+S3+S2+count+S4;
        //System.out.println(S1);
        c5.ajouterRequete(S1);
        c5.executeUpdate(c5.requetes.get(0));
    }
}
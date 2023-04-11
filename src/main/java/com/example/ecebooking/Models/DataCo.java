package com.example.ecebooking.Models;

import com.example.ecebooking.Controllers.Admin.Admin;
import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Models.DataBaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataCo {

    public void SQL_Data_Login(ArrayList<Client> Client) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "");

        c1.ajouterTable("client");
        c1.ajouterRequete("SELECT * FROM `client` ");
        for(int i=0;i<c1.requetes.size();i++)
        {

            for(int j=0;j<c1.remplirChampsRequete(c1.requetes.get(i)).size();j++)
            {
                //passage de la bdd sous la forme d une liste d'hebergment
                String str = c1.remplirChampsRequete(c1.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                String nom = words[0];
                String utilisateur = words[1];
                String mdp = words[2];
                int id = Integer.parseInt(words[3]);

                Client C = new Client( nom, utilisateur, mdp, id , null);
                Client.add(C);
            }
        }


    }


    public void SQL_Data_Admin(ArrayList<Admin> Admin) throws SQLException, ClassNotFoundException {
        DataBaseConnection c3 = new DataBaseConnection("bdd_projets6", "root", "");

        c3.ajouterTable("admin");
        c3.ajouterRequete("SELECT * FROM `admin` ");
        for(int i=0;i<c3.requetes.size();i++)
        {

            for(int j=0;j<c3.remplirChampsRequete(c3.requetes.get(i)).size();j++)
            {
                //passage de la bdd sous la forme d une liste d'hebergment
                String str = c3.remplirChampsRequete(c3.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                String nom = words[0];
                String utilisateur = words[1];
                String mdp = words[2];
                int id = Integer.parseInt(words[3]);

                Admin C = new Admin( nom, utilisateur, mdp, id , null);
                Admin.add(C);
            }
        }


    }


    public void Data_Creation_Login(String nom, String id, String mdp, int num) throws SQLException, ClassNotFoundException {
        DataBaseConnection c1 = new DataBaseConnection("bdd_projets6", "root", "");
        String Snum= String.valueOf(num);
        String S1 ="INSERT INTO `client` (`nom`, `utilisateur`, `mdp`, `id`) VALUES ('";
        String S2="'";
        String S3=", ";
        String S4="')";
        S1=S1+nom+S2+S3+S2+id+S2+S3+S2+mdp+S2+S3+S2+Snum+S4;
        c1.ajouterRequete(S1);
        c1.executeUpdate(c1.requetes.get(0));
    }


    public void SQL_Data_Hebergements2(ArrayList<Hebergement> hebergements) throws SQLException, ClassNotFoundException {

        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "");
        c2.ajouterTable("etablissement");
        //recherche de tous les etablisemeent dans la base de donnée
        c2.ajouterRequete("SELECT * FROM `etablissement` ");
        for(int i=0;i<c2.requetes.size();i++)
        {

            for(int j=0;j<c2.remplirChampsRequete(c2.requetes.get(i)).size();j++)
            {
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

        ArrayList<Hebergement>  hebergements = new ArrayList<>();
        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "root");
        c2.ajouterTable("etablissement");
        c2.ajouterRequete(request);

        for(int i=0;i<c2.requetes.size();i++)
        {

            for(int j=0;j<c2.remplirChampsRequete(c2.requetes.get(i)).size();j++)
            {
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

    public void afficherListeHebergements(ArrayList<Hebergement> liste) {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        for (Hebergement h : liste) {
            System.out.println("Hébergement :");
            System.out.println("Nom : " + h.getNom_etablissement());
            System.out.println("Ville : " + h.getVille());
            System.out.println("Prix : " + h.getPrix());
            // ... afficher d'autres attributs selon votre structure de données
            System.out.println("--------------------");
        }
    }

    public void afficherListeClient(ArrayList<Client> liste) {
        // Parcourir la liste d'hébergements et afficher les informations de chaque hébergement
        for (Client C : liste) {
            System.out.println("Liste de client :");
            System.out.println("Nom d utilisateur : " + C.getId());
            System.out.println("Mdp du client  : " + C.getMdp());
            System.out.println("Num du client : " + C.getNumero());
            // ... afficher d'autres attributs selon votre structure de données
            System.out.println("--------------------");
        }
    }
}

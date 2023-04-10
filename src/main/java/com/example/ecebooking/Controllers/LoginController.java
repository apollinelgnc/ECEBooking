package com.example.ecebooking.Controllers;

import com.example.ecebooking.Controllers.Client.Client;
import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public ChoiceBox acc_selector = new ChoiceBox<>();
    public Label id;
    public TextField id_entree;
    public PasswordField mot_de_passe;
    public Button button_valider;
    ArrayList<Client> membres=new ArrayList<>();
    ArrayList<Hebergement>hebergements=new ArrayList<>();
    private PreparedStatement stmt;

    public void initialize(URL url, ResourceBundle resourceBundle){
    button_valider.setOnAction(actionEvent -> {
        Model.getInstance().getViewFactory().ClientView();

    }
    );
}
private void onLogin(){
    Model.getInstance().getViewFactory().ClientView();
}

  /*  private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public ArrayList<Client> getMembres() {
        return membres;
    }

    public void setMembres(Client client) {
        this.membres.add(client);
    }
    public void setHebergements(Hebergement hebergement) {
        this.hebergements.add(hebergement);
    }

    public void Choice( ){
        acc_selector.getItems().add("Client");
        acc_selector.getItems().add("Admin");
    }
    public void Login(ActionEvent event) throws SQLException, ClassNotFoundException {
        Choice();
        SQL_Data_Login();
        SQL_Data_Hebergements();

        Window owner = button_valider.getScene().getWindow();
        System.out.println(id_entree.getText());
        System.out.println(mot_de_passe.getText());
        boolean equal=false;
        for(int i=0; i<membres.size();i++){
            if(id_entree.getText().equals(membres.get(i).getId())){
                equal=true;
                System.out.println("hello " +membres.get(i).getId());
            }

        }
        System.out.println(equal);
        if (!equal) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "mauvais id");
            return;
        }
        if (id_entree.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Veuillez entrer votre id");
            return;
        }
        if (mot_de_passe.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Veuillez entrer votre mdp");
            return;
        }
        String id = id_entree.getText();
        String password = mot_de_passe.getText();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Login réussi !",
                "Welcome " + id);

    }*/
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
                for (String word : words) {
                    System.out.println(word);
                }

                String nom = words[0];
                String utilisateur = words[1];
                String mdp = words[2];
                int id = Integer.parseInt(words[3]);

                Client C = new Client( nom, utilisateur, mdp, id , null);
                Client.add(C);
            }
        }


    }
    public void SQL_Data_Hebergements(ArrayList<Hebergement> hebergements) throws SQLException, ClassNotFoundException {

        DataBaseConnection c2 = new DataBaseConnection("bdd_projets6", "root", "");
        c2.ajouterTable("etablissement");
        //requetes sql qui me permet de chercher un type en particulier en fonction de la demande

        //recherche de tous les etablisemeent dans la base de donnée
        c2.ajouterRequete("SELECT * FROM `etablissement` ");

        for(int i=0;i<c2.requetes.size();i++)
        {

            for(int j=0;j<c2.remplirChampsRequete(c2.requetes.get(i)).size();j++)
            {
                    //passage de la bdd sous la forme d une liste d'hebergment
                String str = c2.remplirChampsRequete(c2.requetes.get(i)).get(j).toString();
                String[] words = str.split(",");
                for (String word : words) {
                    System.out.println(word);
                }

                String nom_etablissement = words[0];
                String ville = words[1];
                int nombre_chambres = Integer.parseInt(words[2]);
                int nombre_places = Integer.parseInt(words[3]);
                int prix = Integer.parseInt(words[4]);
                int distanceCentre = Integer.parseInt(words[5]);
                int idhebergement = Integer.parseInt(words[6]);
                Hebergement h = new Hebergement(nom_etablissement, ville, nombre_chambres, nombre_places, prix, distanceCentre, idhebergement);
                hebergements.add(h);
            }
        }

        System.out.println("coockie4");

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
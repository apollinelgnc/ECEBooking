package com.example.ecebooking.Controllers;

import com.example.ecebooking.Controllers.Client.Membre;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LoginController {
    public ChoiceBox acc_selector;
    public Label id;
    public TextField id_entree;
    public PasswordField mot_de_passe;
    public Button button_valider;
    ArrayList<Membre> membres=new ArrayList<>();

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public ArrayList<Membre> getMembres() {
        return membres;
    }

    public void setMembres(Membre membre) {
        this.membres.add(membre);
    }

    public void Login(ActionEvent event) throws SQLException {
        SQL_Data();

      /*
      LAISSER LE CODE
      Window owner = button_valider.getScene().getWindow();
        System.out.println(id_entree.getText());
        System.out.println(mot_de_passe.getText());

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
        */
    }

    public void SQL_Data() throws SQLException {
        DataBaseConnection connection = new DataBaseConnection();
        Statement stmt = connection.getConnection().createStatement();
        ResultSet res = stmt.executeQuery("SELECT * FROM login.user_client");
        while (res.next()) {
            Membre m = new Membre(res.getString("name_client"), res.getInt("User_client"), res.getString("idUser_client"), res.getString("mdptUse_client"));
            System.out.println(m.toString());
            setMembres(m);
        }
    }
}

package com.example.ecebooking.Controllers.Hebergements;

import com.example.ecebooking.Models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PayementController {

    @FXML
    private ToggleButton credit, debit;
    @FXML
    private TextField numCarte, anneeExp, nom, cvv;
    @FXML
    private ChoiceBox<String> moisExp;
    @FXML
    private Button annuler, valider;
    @FXML
    private Label erreur;
    private boolean validation;

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public void initialize() {
        validation = false;
        valider.setOnAction(event -> {
            try {
                int num = Integer.parseInt(numCarte.getText());
                int cvvCarte = Integer.parseInt(cvv.getText());
                int annee = Integer.parseInt(anneeExp.getText());

            } catch (NumberFormatException e) {
                erreur.setText("Impossible de valider le payement");
            }
            if (numCarte.getText().length() != 16 || cvv.getText().length() != 3 || anneeExp.getText().length() != 4)
                erreur.setText("Impossible de valider le payement");
            else {
                validation=true;
                Model.getInstance().getViewFactory().closeStage();
            }
        });
        annuler.setOnAction(event -> Model.getInstance().getViewFactory().closeStage());
    }

}

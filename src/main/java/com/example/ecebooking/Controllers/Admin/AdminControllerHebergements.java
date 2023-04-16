package com.example.ecebooking.Controllers.Admin;

import com.example.ecebooking.Controllers.Hebergements.Hebergement;
import com.example.ecebooking.Models.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminControllerHebergements {
    @FXML
    private Button menu_button = new Button();
    @FXML
    private Button supprimer = new Button();
    @FXML
    private Button ajouterPromos = new Button();
    @FXML
    private Button ajouterHebergement = new Button();
    @FXML
    private Button actuHebergement = new Button();
    @FXML
    private Button log_out_button = new Button();
    @FXML
    private Button ajouterGo = new Button();
    @FXML
    private Button supprimerGo = new Button();
    @FXML
    private Button valider = new Button();
    @FXML
    private TableView<Hebergement> tableView;
    @FXML
    private TableColumn<Hebergement, String> nom_;
    @FXML
    private TableColumn<Hebergement, String> ville_;
    @FXML
    private TableColumn<Hebergement, String> reductions_;
    @FXML
    private TableColumn<Hebergement, String> id_;
    @FXML
    private TableColumn<Hebergement, String> menage_;
    @FXML
    private TableColumn<Hebergement, String> fumeur_;
    @FXML
    private TableColumn<Hebergement, String> wifi_;
    @FXML
    private TableColumn<Hebergement, String> distanceCentre_;
    @FXML
    private TableColumn<Hebergement, String> prix_;
    @FXML
    private TableColumn<Hebergement, String> nbPlace_;
    @FXML
    private TableColumn<Hebergement, String> nbChambre_;
    private Admin admin;
    @FXML
    private Label id1, erreur, promo, Tapeznom, Tapezchambre, Tapezville, Tapezplace, Tapezprix, Tapezdistance, Tapezwifi, Tapezfumeur, Tapezmenage, Tapezreduc;
    @FXML
    private TextField Tapezid, Tapezpromo, nom, ville, nbChambre, place, prix, distance, wifi, menage, fumeur, reduc;

    private ArrayList<Hebergement> tempo;

    public AdminControllerHebergements(Admin ad) throws Exception {
        admin = ad;
        admin.AdminChargement();
    }

    public void initialize() throws Exception {
        nom_.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom_etablissement()));
        ville_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getVille())));
        nbChambre_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNombre_chambres())));
        nbPlace_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNombre_places())));
        prix_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix())));
        distanceCentre_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDistanceCentre())));
        wifi_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getWifi())));
        menage_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getMenage())));
        fumeur_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getFumeur())));
        id_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getIdhebergement())));
        reductions_.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPromo())));
        loadData();
        admin.afficherListeHebergements(tempo);
        menu_button.setOnAction(event -> {
            try {
                Model.getInstance().getViewFactory().AdminView(admin);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        log_out_button.setOnAction(event -> Model.getInstance().getViewFactory().LoginView());
        supprimer.setOnAction(event -> {
            Tapezid.setVisible(true);
            id1.setVisible(true);
            supprimerGo.setVisible(true);
            afficherEtiquettesAj(false);
            Tapezpromo.setVisible(false);
            promo.setVisible(false);
            ajouterGo.setVisible(false);
            supprimerGo.setOnAction(event1 -> {
                try {
                    admin.SuppHergement(Tapezid.getText());
                    loadData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
        ajouterHebergement.setOnAction(event -> {
            Tapezid.setVisible(false);
            id1.setVisible(false);
            supprimerGo.setVisible(false);
            Tapezpromo.setVisible(false);
            promo.setVisible(false);
            ajouterGo.setVisible(false);
            afficherEtiquettesAj(true);
            valider.setOnAction(event2 -> {
                try {
                    if (ville.getText() == null || nom.getText() == null || prix.getText() == null ||
                            distance.getText() == null || nbChambre.getText() == null || place.getText() == null
                            || wifi.getText() == null || menage.getText() == null || fumeur.getText() == null ||
                            !prix.getText().matches("\\d+(\\.\\d+)?"))  {
                        erreur.setVisible(true);
                    } else {
                        erreur.setVisible(false);
                        admin.AjoutHbergement(ville.getText(), nom.getText(), prix.getText(), distance.getText(), nbChambre.getText(), place.getText(), distance.getText(), wifi.getText(), menage.getText(), fumeur.getText());
                        loadData();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
        actuHebergement.setOnAction(event -> {
            afficherEtiquettesAj(false);
            Tapezid.setVisible(true);
            id1.setVisible(true);
            supprimerGo.setVisible(true);
            supprimerGo.setText("Valider id");
            supprimerGo.setOnAction(event1 -> {
                try {
                    Tapezid.setVisible(false);
                    id1.setVisible(false);
                    supprimerGo.setVisible(false);
                    afficherEtiquettesAj(true);
                    System.out.println(tempo.size());
                    for (int i = 0; i < tempo.size(); i++) {
                        if (Integer.parseInt(Tapezid.getText()) == (tempo.get(i).getIdhebergement())) {
                            nbChambre.setText(String.valueOf(tempo.get(i).getNombre_chambres()));
                            place.setText(String.valueOf(tempo.get(i).getNombre_places()));
                            distance.setText(String.valueOf(tempo.get(i).getDistanceCentre()));
                            fumeur.setText(tempo.get(i).getFumeur());
                            menage.setText(tempo.get(i).getMenage());
                            nom.setText(tempo.get(i).getNom_etablissement());
                            prix.setText(String.valueOf(tempo.get(i).getPrix()));
                            reduc.setText(String.valueOf(tempo.get(i).getPromo()));
                            ville.setText(tempo.get(i).getVille());
                            wifi.setText(tempo.get(i).getWifi());
                        }
                    }
                    valider.setOnAction(event2 -> {
                        try {
                            if (ville.getText() == null || nom.getText() == null || prix.getText() == null ||
                                    distance.getText() == null || nbChambre.getText() == null || place.getText() == null
                                    || wifi.getText() == null || menage.getText() == null || fumeur.getText() == null ||
                                    !prix.getText().matches("\\d+(\\.\\d+)?")) {
                                erreur.setVisible(true);
                            } else {
                                erreur.setVisible(false);
                                admin.ActuHergement(Tapezid.getText(), nom.getText(), ville.getText(), Integer.parseInt(nbChambre.getText()), Integer.parseInt(place.getText()), Integer.parseInt((prix.getText())), Integer.parseInt(distance.getText()), wifi.getText(), menage.getText(), fumeur.getText());
                                loadData();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });
        ajouterPromos.setOnAction(event -> {
            afficherEtiquettesAj(false);
            Tapezid.setVisible(true);
            id1.setVisible(true);
            Tapezpromo.setVisible(true);
            promo.setVisible(true);
            ajouterGo.setVisible(true);
            ajouterGo.setOnAction(event1 -> {
                try {
                    admin.PromoH(Tapezid.getText(), Tapezpromo.getText());
                    loadData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        });

    }

    private void loadData() {
        try {
            admin.AdminChargement();
            ArrayList<Hebergement> hebergementList = admin.getListHebergement();
            tempo = hebergementList;
            tableView.getItems().clear();
            tableView.getItems().addAll(hebergementList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void afficherEtiquettesAj(boolean tempo) {
        Tapezchambre.setVisible(tempo);
        Tapezplace.setVisible(tempo);
        Tapezdistance.setVisible(tempo);
        Tapezfumeur.setVisible(tempo);
        Tapezmenage.setVisible(tempo);
        Tapeznom.setVisible(tempo);
        Tapezprix.setVisible(tempo);
        Tapezreduc.setVisible(tempo);
        Tapezville.setVisible(tempo);
        Tapezwifi.setVisible(tempo);
        nom.setVisible(tempo);
        ville.setVisible(tempo);
        wifi.setVisible(tempo);
        reduc.setVisible(tempo);
        menage.setVisible(tempo);
        fumeur.setVisible(tempo);
        place.setVisible(tempo);
        nbChambre.setVisible(tempo);
        distance.setVisible(tempo);
        prix.setVisible(tempo);
        valider.setVisible(tempo);
    }
}



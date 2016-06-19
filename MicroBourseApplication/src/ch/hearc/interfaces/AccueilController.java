package ch.hearc.interfaces;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.metiers.Entreprise;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 * Classe permettant de controller le composant graphique "Accueil"
 * Ce composant contient l'ensemble des tuiles représentant une entreprise
 */
public class AccueilController implements Initializable {

    @FXML
    private TilePane tilePane;

    /**
     * Initialise le controlleur
     *
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTuiles();
    }
    
    /**
     * Récupération de la liste des entreprises et affichage des informations
     * dans des tuiles
     */
    public void showTuiles() {
        FXMLLoader loader;
        TitledPane tp;
        EntrepriseDaoImplement manager = new EntrepriseDaoImplement();
        List<Entreprise> entreprises = manager.getEntreprises();
        int i = 0;
        try {
            for(Entreprise e : entreprises) {
                if(i < 6) {
                    loader = new FXMLLoader(getClass().getResource("Tuile.fxml"));
                    tp = (TitledPane) loader.load();
                    TuileController tc = loader.getController();
                    tc.setID(e.getIdEntreprise());
                    tp.setText(e.getNom());
                    tilePane.getChildren().add(tp);
                    i++;
                } else {
                    return;
                }
            }
        } catch (IOException ex) {
           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}

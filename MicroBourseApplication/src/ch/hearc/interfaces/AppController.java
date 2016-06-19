package ch.hearc.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 * Classe permettant de controller le composant graphique "App"
 * Il s'agit du corps de l'application, 
 * celui-ci contiendra l'ensemble des composants graphiques
 */
public class AppController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnMyAccount;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Menu fileMenu;
    @FXML
    private Menu aboutMenu;
    
    private static AnchorPane masterPane;
    private static long ID_USER;
    
    /**
     * Initialise le controlleur
     *
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        masterPane = new AnchorPane();
        borderPane.setCenter(masterPane);
        loadFxmlToMasterPane("Accueil.fxml");
        createFileMenu();
        createHelpMenu();
    }

    /**
     * Chargement du composant "Accueil" lors du clique du bouton "Accueil"
     * @param event 
     */
    @FXML
    private void btnHome_onAction(ActionEvent event) {
        loadFxmlToMasterPane("Accueil.fxml");  
    }

    /**
     * Chargement du composant "Account" lors du clique du bouton "Account"
     * @param event 
     */
    @FXML
    private void btnMyAccount_onAction(ActionEvent event) {
        loadFxmlToMasterPane("Account.fxml");
    }
    
    /**
     * Effectue le chargement du composant à afficher
     * @param fxml 
     */
    public void loadFxmlToMasterPane(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            StackPane sp = (StackPane) loader.load();
            masterPane.getChildren().clear();
            masterPane.getChildren().add(sp);
            animatePane();
        } catch (IOException ex) {
           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    /**
     * Obtention de l'id de l'utilisateur connecté
     * @param id : ID utilisateur
     */    
    public void setUserID(Long id) {
        this.ID_USER = id;
    }
    
    /**
     * Envoie de l'id de l'utilisateur connecté
     * @return ID utilisateur
     */
    public static long getUserID() {
        return ID_USER;
    }
    
    /**
     * Affiche les détails d'une entreprise
     * Cette méthode est appeler par le composant permettant 
     * d'afficher les détails d'une entreprise (par exemple une Tuile)
     * @param detail : "Page à afficher" 
     */    
    public static void viewDetails(StackPane detail) {
        masterPane.getChildren().clear();
        masterPane.getChildren().add(detail);
        animatePane();
    }
    
    /**
     * Animation lors de l'affichage d'un composant graphique
     */
    public static void animatePane() {
        FadeTransition ft = new FadeTransition(Duration.millis(500), masterPane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();   
    }
    
    /**
     * Création du menu d'aide
     */
    public void createHelpMenu() {
        MenuItem about = new MenuItem("A Propos");
        about.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("A Propos");
            alert.setHeaderText("Microbourse v1.0.0");
            alert.setContentText("Microbourse\n"
                    + "\tProjet P2 INF 2015-2016\n"
                    + "\tVersion 1.0.0\n"
                    + "\n"
                    + "Auteurs:\n"
                    + "\tVaucher Joël\n"
                    + "\tOmbang Ndo Charles\n"
                    + "\tRodrigues Lourenço Daniel");
            alert.showAndWait();   
        });
        
        aboutMenu.getItems().addAll(about);
    }
    
    /**
     * Création du menu fichier
     */
    public void createFileMenu() {
        MenuItem exit = new MenuItem("Quitter");
        exit.setOnAction(e -> {
            System.exit(0);
        });
        
        fileMenu.getItems().add(exit);
    }
}

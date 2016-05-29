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
    private Menu editMenu;
    @FXML
    private Menu helpMenu;
    
    private static AnchorPane masterPane;
    
    /**
     * Initializes the controller class.
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
     * 
     * @param event 
     */
    @FXML
    private void btnHome_onAction(ActionEvent event) {
        loadFxmlToMasterPane("Accueil.fxml");  
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void btnMyAccount_onAction(ActionEvent event) {
        loadFxmlToMasterPane("Account.fxml");
    }
    
    /**
     * 
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
     * 
     * @param detail 
     */    
    public static void viewDetails(StackPane detail) {
        masterPane.getChildren().clear();
        masterPane.getChildren().add(detail);
        animatePane();
    }
    
    /**
     * 
     */
    public static void animatePane() {
        FadeTransition ft = new FadeTransition(Duration.millis(600), masterPane);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();   
    }
    
    /**
     * 
     */
    public void createHelpMenu() {
        MenuItem about = new MenuItem("A Propos");
        about.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("A Propos");
            alert.setHeaderText("Microbourse");
            alert.setContentText("Microbourse\n Projet P2 INF 2015-2016"
                    + "\n\n"
                    + "Auteurs:\n"
                    + "\tVaucher Joël\n"
                    + "\tOmbang Ndo Charles\n"
                    + "\tRodrigues Lourenço Daniel");
            alert.showAndWait();   
        });
        
        MenuItem help = new MenuItem("Aide");
        help.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ade");
            alert.setHeaderText("Microbourse");
            alert.setContentText("Un peu d'aide ?");
            alert.showAndWait();   
        });
        
        helpMenu.getItems().addAll(help, about);
    }
    
    /**
     * 
     */
    public void createFileMenu() {
        MenuItem exit = new MenuItem("Quitter");
        exit.setOnAction(e -> {
            System.exit(0);
        });
        
        fileMenu.getItems().add(exit);
    }
}

package ch.hearc.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 */
public class AppController implements Initializable {

    @FXML
    private AnchorPane masterPane;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnMyAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        try {
            StackPane sp = (StackPane) loader.load();
            masterPane.getChildren().setAll(sp);
        } catch (IOException ex) {
           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlerHome(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        try {
            StackPane sp = (StackPane) loader.load();
            masterPane.getChildren().clear();
            masterPane.getChildren().add(sp);
            btnMyAccount.setDisable(false);
            btnHome.setDisable(true);
        } catch (IOException ex) {
           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    @FXML
    private void HandlerMyAccount(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
        try {
            StackPane sp = (StackPane) loader.load();
            masterPane.getChildren().clear();
            masterPane.getChildren().add(sp);
            btnMyAccount.setDisable(true);
            btnHome.setDisable(false);
        } catch (IOException ex) {
           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AnchorPane getMasterPane() {
        return masterPane;
    }
    
    public Button getBtnHome() {
        return btnHome;
    }
    public Button getBtnMyAccount() {
        return btnMyAccount;
    }

}

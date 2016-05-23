package ch.hearc.interfaces;

import java.io.IOException;
import java.net.URL;
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
 */
public class AccueilController implements Initializable {

    @FXML
    private TilePane tilePane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader loader;
        TitledPane tp;
        try {
            for(int i = 1; i <= 6; i++) {
                loader = new FXMLLoader(getClass().getResource("Tuile.fxml"));
                tp = (TitledPane) loader.load();
                TuileController tc = loader.getController();
                tc.setID(i);
                tp.setText("Produit " + i);
                tilePane.getChildren().add(tp);
            }
        } catch (IOException ex) {
           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}

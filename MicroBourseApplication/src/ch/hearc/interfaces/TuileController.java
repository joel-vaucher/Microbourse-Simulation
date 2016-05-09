package ch.hearc.interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 */
public class TuileController implements Initializable {

    @FXML
    private TitledPane tilePane;
    @FXML
    private Label lblPriceRequested;
    @FXML
    private Label lblSharesSale;
    @FXML
    private Button btnRequest;
    @FXML
    private Button btnOffer;
    @FXML
    private TextField txtNbShares;
    @FXML
    private TextField txtPriceShares;
    @FXML
    private Button btnRequestAP;
    @FXML
    private Button btnOfferAP;
    @FXML
    private TextField txtNbSharesAP;
    @FXML
    private Hyperlink linkDetail;
    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private Label lblPriceOffered;
    
    private int ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onMouseClicked_linkDetail(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
        StackPane sp = null;
        try {
            sp = (StackPane) loader.load();
            DetailController detail = loader.getController();
            detail.setID(ID);
            AppController.viewDetails(sp);
        } catch (IOException ex) {
           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void setID(int id) {
       this.ID = id;
    }
}

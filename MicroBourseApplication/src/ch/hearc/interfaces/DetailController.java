package ch.hearc.interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author daniel.rodrigue
 */
public class DetailController implements Initializable {

    @FXML
    private Label lblPriceOffered;
    @FXML
    private Label lblPriceRequested;
    @FXML
    private TextField txtNbSharesAP;
    @FXML
    private Button btnRequestAP;
    @FXML
    private Button btnOfferAP;
    @FXML
    private Button btnRequest;
    @FXML
    private Button btnOffer;
    @FXML
    private TextField txtNbShares;
    @FXML
    private TextField txtPriceShares;
    @FXML
    private TableView<?> tableOffers;
    @FXML
    private TableView<?> tableRequests;
    @FXML
    private Label lblProductName;
    @FXML
    private LineChart<?, ?> chart;
    
    private int ID;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void btnRequestAP_onAction(ActionEvent event) {
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void btnOfferAP_onAction(ActionEvent event) {
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void btnRequest_onAction(ActionEvent event) {
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void btnOffer_onAction(ActionEvent event) {
    }
    
    /**
     * 
     * @param id 
     */
    public void setID(int id) {
        ID = id;
        lblProductName.setText("Produit " + ID);
    }
}

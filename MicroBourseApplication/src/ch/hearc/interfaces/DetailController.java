package ch.hearc.interfaces;

import java.net.URL;
import java.util.ResourceBundle;
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
    private LineChart<?, ?> graph;
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
    
    private int ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setID(int id) {
        ID = id;
        lblProductName.setText("Produit " + ID);
    }
    
}

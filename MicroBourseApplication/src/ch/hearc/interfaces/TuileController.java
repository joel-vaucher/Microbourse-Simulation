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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 */
public class TuileController extends AbstractActions implements Initializable {

    @FXML
    private TitledPane tilePane;
    @FXML
    private Label lblSharesSale;
    @FXML
    private TextField txtNbShares;
    @FXML
    private TextField txtPriceShares;
    @FXML
    private TextField txtNbSharesAP;
    @FXML
    private Hyperlink linkDetail;
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private Label lblPriceBuy;
    @FXML
    private Label lblPriceSale;
    @FXML
    private Button btnSale;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnSaleAP;
    @FXML
    private Button btnBuyAP;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Parametrage du graphe
        NumberAxis yAxis = (NumberAxis) chart.getYAxis();
        yAxis.setAutoRanging(true);
        yAxis.setForceZeroInRange(false);
    }    
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void linkDetail_onAction(ActionEvent event) {
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

    
    /**
     * Obtention de l'id de l'entreprise associée à la tuile
     * @param id 
     */    
    public void setID(Long id) {
        this.ID = id;
        showChart(this.chart);
    }
    
    /**
     * 
     */
    public void showPrices() {
    }

    @FXML
    private void btnSale_noAction(ActionEvent event) {
    }

    @FXML
    private void btnBuy_onAction(ActionEvent event) {
    }

    @FXML
    private void btnSaleAP_onAction(ActionEvent event) {
    }

    @FXML
    private void btnBuyAP_onAction(ActionEvent event) {
    }
}

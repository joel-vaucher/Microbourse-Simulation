package ch.hearc.interfaces;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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
    private LineChart<String, Number> chart;
    @FXML
    private Label lblPriceOffered;
    
    private long ID;

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
     * 
     * @param event 
     */
    @FXML
    private void btnRequest_noAction(ActionEvent event) {
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
     * Obtention de l'id de l'entreprise associée ä la tuile
     * @param id 
     */    
    public void setID(Long id) {
        this.ID = id;                   
        showChart();
    }
    
    /**
     * 
     * @param manager
     */
    public void showChart() {
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> offresVente = soo.getCurrentSellOffersByEntreprise(this.ID);
        List<Offre> offresAchat = soo.getCurrentPurchaseOffersByEntreprise(this.ID);
        
        XYChart.Series achatSeries = new XYChart.Series();
        XYChart.Series venteSeries = new XYChart.Series();
        achatSeries.setName("Offre");
        venteSeries.setName("Demande");
        
        for (Offre offre : offresAchat) {            
            achatSeries.getData().add(new XYChart.Data(offre.getDate().toString(), offre.getPrix()));
        }
        for (Offre offre : offresVente) {
            venteSeries.getData().add(new XYChart.Data(offre.getDate().toString(), offre.getPrix()));
        }
        
        // Affichage des trois series
        chart.getData().addAll(achatSeries, venteSeries);
    }
    
    /**
     * 
     */
    public void showPrices() {
    }
}

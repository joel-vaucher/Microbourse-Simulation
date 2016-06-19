package ch.hearc.interfaces;

import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
 * Classe permettant de controller le composant graphique "Tuile"
 * Le composant "Tuile" permet d'afficher un résumé d'une entreprise sur l'accueil
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
     * Initialise le controlleur
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
        // Lancement du timer de rafraichissement
        Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    showChart(chart);
                    showPrices(lblPriceBuy, lblPriceSale);
                });
            }
        }, 0, 60000);
    }    
    
    /**
     * Affiche le détail d'une entreprise lors du clique du lien "Détail"
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
        showPrices(lblPriceBuy, lblPriceSale);
    }
    
    /**
     * Effectue une vente au prix actuel lors du clique du bouton "Vendre"
     * @param event 
     */
    @FXML
    private void btnSaleAP_onAction(ActionEvent event) {
        String nbAction = txtNbSharesAP.getText().equals("") ? "0" : txtNbSharesAP.getText();
        saleAP(Integer.parseInt(nbAction));
    }

    /**
     * Effectue un achat au prix actuel lors du clique du bouton "Acheter"
     * @param event 
     */
    @FXML
    private void btnBuyAP_onAction(ActionEvent event) {
        String nbAction = txtNbSharesAP.getText().equals("") ? "0" : txtNbSharesAP.getText();
        buyAP(Integer.parseInt(nbAction));
    }
    
    /**
     * Effectue une vente au prix indiqué lors du clique du bouton "Vendre"
     * @param event 
     */
    @FXML
    private void btnSale_onAction(ActionEvent event) {
        String nbAction = txtNbShares.getText().equals("") ? "0" : txtNbShares.getText();
        String prix = txtPriceShares.getText().equals("") ? "0" : txtPriceShares.getText();
        System.out.println(nbAction + " " + prix);
        sale(Integer.parseInt(nbAction), Integer.parseInt(prix));
    }
    
    /**
     * Effectue un achat au prix indiqué lors du clique du bouton "Acheter"
     * @param event 
     */
    @FXML
    private void btnBuy_onAction(ActionEvent event) {
        String nbAction = txtNbShares.getText().equals("") ? "0" : txtNbShares.getText();
        String prix = txtPriceShares.getText().equals("") ? "0" : txtPriceShares.getText();
        buy(Integer.parseInt(nbAction), Integer.parseInt(prix));
    }

}

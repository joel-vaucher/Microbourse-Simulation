package ch.hearc.interfaces;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 * Classe permettant de controller le composant graphique "Detail"
 * Le composant "Detail" permet d'afficher les détails d'une entreprise
 */
public class DetailController extends AbstractActions implements Initializable {

    @FXML
    private TextField txtNbSharesAP;
    @FXML
    private TextField txtNbShares;
    @FXML
    private TextField txtPriceShares;
    @FXML
    private Label lblProductName;
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private Label lblPriceSale;
    @FXML
    private Label lblPriceBuy;
    @FXML
    private Button btnSaleAP;
    @FXML
    private Button btnBuyAP;
    @FXML
    private Button btnSale;
    @FXML
    private Button btnBuy;
    @FXML
    private TableView<HistoriqueModel> tableSale;
    @FXML
    private TableColumn<HistoriqueModel, String> salePriceCol;
    @FXML
    private TableColumn<HistoriqueModel, String> nbSaleActionCol;
    @FXML
    private TableView<HistoriqueModel> tableBuy;
    @FXML
    private TableColumn<HistoriqueModel, String> buyPriceCol;
    @FXML
    private TableColumn<HistoriqueModel, String> nbBuyActionCol;
    
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
                    refresh();
                });
            }
        }, 0, 60000);
    }
    
    /**
     * Effectue une vente au prix actuel lors du clique du bouton "Vendre"
     * @param event 
     */
    @FXML
    private void btnSaleAP_onAction(ActionEvent event) {
        String nbAction = txtNbSharesAP.getText().equals("") ? "0" : txtNbSharesAP.getText();
        saleAP(Integer.parseInt(nbAction));
        refresh();
    }

    /**
     * Effectue un achat au prix actuel lors du clique du bouton "Acheter"
     * @param event 
     */
    @FXML
    private void btnBuyAP_onAction(ActionEvent event) {
        String nbAction = txtNbSharesAP.getText().equals("") ? "0" : txtNbSharesAP.getText();
        buyAP(Integer.parseInt(nbAction));
        refresh();
    }

    /**
     * Effectue une vente au prix indiqué lors du clique du bouton "Vendre"
     * @param event 
     */
    @FXML
    private void btnSale_onAction(ActionEvent event) {
        String nbAction = txtNbShares.getText().equals("") ? "0" : txtNbShares.getText();
        String prix = txtPriceShares.getText().equals("") ? "0" : txtPriceShares.getText();
        sale(Integer.parseInt(nbAction), Integer.parseInt(prix));
        refresh();
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
        refresh();
    }

    /**
     * Recherche les informations de l'entreprise et affiche :
     *  - Le graphique du cours des actions
     *  - Les tableaux des historiques
     *  - Les prix actuels
     * 
     * @param id : ID entreprise
     */
    public void setID(long id) {
        this.ID = id;
        EntrepriseDaoImplement manager = new EntrepriseDaoImplement();
        Entreprise entreprise = manager.getEntrepriseByID(this.ID);
        lblProductName.setText(entreprise.getNom());
        
        showChart(chart);
        showTables();
        showPrices(lblPriceBuy, lblPriceSale);
    }
    
    /**
     * Rafraichissement des valeurs affichées
     */
    private void refresh() {
        showChart(chart);
        showTables();
        showPrices(lblPriceBuy, lblPriceSale);  
    }
    
    /**
     * Récupération et traitemement des données reçues depuis la BDD
     * afin de peupler les tableaux.
     */
    private void showTables() {
        // Récupération des données
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> offresVente = soo.getCurrentSellOffersByEntreprise(this.ID);
        List<Offre> offresAchat = soo.getCurrentPurchaseOffersByEntreprise(this.ID);

        // Nettoyage des tableaux
        tableBuy.getItems().clear();
        tableSale.getItems().clear();
        
        // Préparation des données à l'aide d'un modèle
        ObservableList<HistoriqueModel> achats = FXCollections.observableArrayList();
        for (Offre offre : offresAchat) {
            achats.add(new HistoriqueModel(offre));
        }
        
        ObservableList<HistoriqueModel> ventes = FXCollections.observableArrayList();
        for (Offre offre : offresVente) {
            ventes.add(new HistoriqueModel(offre));
        }
        
        // Association des attributs du modèle aux colonnes du tableau
        buyPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        nbBuyActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
        
        salePriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        nbSaleActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
                
        // Peuplement des tableaux
        tableBuy.setItems(achats);
        tableSale.setItems(ventes);
    }
}

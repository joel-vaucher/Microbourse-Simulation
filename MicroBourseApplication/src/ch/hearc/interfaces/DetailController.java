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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
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
     * Initializes the controller class.
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
     * 
     * @param event 
     */
    @FXML
    private void btnSaleAP_onAction(ActionEvent event) {
        String nbAction = txtNbSharesAP.getText().equals("") ? "0" : txtNbSharesAP.getText();
        saleAP(Integer.parseInt(nbAction));
        refresh();
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void btnBuyAP_onAction(ActionEvent event) {
        String nbAction = txtNbSharesAP.getText().equals("") ? "0" : txtNbSharesAP.getText();
        buyAP(Integer.parseInt(nbAction));
        refresh();
    }

    /**
     * 
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
     * 
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
     * 
     * @param id 
     */
    public void setID(long id) {
        this.ID = id;
        EntrepriseDaoImplement manager = new EntrepriseDaoImplement();
        Entreprise entreprise = manager.getEntrepriseByID(this.ID);
        lblProductName.setText(entreprise.getNom());
        
        showChart(chart);
        showTables();
        showPrices();
    }
    
    private void refresh() {
        showChart(chart);
        showTables();
        showPrices();  
    }
    
    /**
     * 
     */
    private void showTables() {
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> offresVente = soo.getCurrentSellOffersByEntreprise(this.ID);
        List<Offre> offresAchat = soo.getCurrentPurchaseOffersByEntreprise(this.ID);

        ObservableList<HistoriqueModel> achats = FXCollections.observableArrayList();
        for (Offre offre : offresAchat) {
            achats.add(new HistoriqueModel(offre));
        }
        
        ObservableList<HistoriqueModel> ventes = FXCollections.observableArrayList();
        for (Offre offre : offresVente) {
            ventes.add(new HistoriqueModel(offre));
        }
        
        buyPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        nbBuyActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
        
        salePriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        nbSaleActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
                
        tableBuy.setItems(achats);
        tableSale.setItems(ventes);
    }
    
    /**
     * 
     */
    private void showPrices() {
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> offresVente = soo.getBestOffersByDay(this.ID, Offre.operationType.VENTE);
        List<Offre> offresAchat = soo.getBestOffersByDay(this.ID, Offre.operationType.ACHAT);
        
        if(!offresAchat.isEmpty()) {
            lblPriceBuy.setText(offresAchat.get(offresAchat.size()-1).getPrix()+"");
        }
        if(!offresVente.isEmpty()) {
            lblPriceSale.setText(offresVente.get(offresVente.size()-1).getPrix()+"");
        }
    }
}

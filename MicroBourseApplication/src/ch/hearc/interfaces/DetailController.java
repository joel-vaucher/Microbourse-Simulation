package ch.hearc.interfaces;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
    private TableView<ModeleHistorique> tableOffers;
    @FXML
    private TableView<ModeleHistorique> tableRequests;
    @FXML
    private Label lblProductName;
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private TableColumn<ModeleHistorique, Number> uniteOfferPriceCol;
    @FXML
    private TableColumn<ModeleHistorique, Number> uniteRequestPriceCol;
    @FXML
    private TableColumn<ModeleHistorique, Number> nbOfferActionCol;
    @FXML
    private TableColumn<ModeleHistorique, Number> nbRequestActionCol;
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
    private void btnSaleAP_onAction(ActionEvent event) {
        String nbAction = txtNbSharesAP.getText().equals("") ? "0" : txtNbSharesAP.getText();
        saleAP(Integer.parseInt(nbAction));
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void btnBuyAP_onAction(ActionEvent event) {
        String nbAction = txtNbSharesAP.getText().equals("") ? "0" : txtNbSharesAP.getText();
        buyAP(Integer.parseInt(nbAction));
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
    }
    
    /**
     * 
     */
    public void showSalesTable() {
        EntrepriseDaoImplement manager = new EntrepriseDaoImplement();
        List<HistoriqueEntreprise> historique = manager.getHistoriqueOf(this.ID);

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // ObservableList<String> data;
        for (HistoriqueEntreprise he : historique) {
            
        }
        
        uniteOfferPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        nbOfferActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
    }
    
    /**
     * 
     */
    public void showPurchasesTable() {
        
    }
}

class ModeleHistorique {
    private final SimpleStringProperty unitePrice;
    private final SimpleStringProperty nbActions;
    
    private ModeleHistorique(String up, String nba) {
        this.unitePrice = new SimpleStringProperty(up);
        this.nbActions = new SimpleStringProperty(nba);
    }
    
    public String getUnitePrice() {
        return unitePrice.get();
    }
    
    public void setUnitePrice(String un) {
        unitePrice.set(un);
    }
    
    public String getNbActions() {
        return nbActions.get();
    }
    
    public void setNbActions(String nba) {
        nbActions.set(nba);
    }  
}

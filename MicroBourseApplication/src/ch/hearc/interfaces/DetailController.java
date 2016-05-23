package ch.hearc.interfaces;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
    private long ID;
    
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
    public void setID(long id) {
        ID = id;
        EntrepriseDaoImplement manager = new EntrepriseDaoImplement();
        Entreprise entreprise = manager.getEntrepriseByID(ID);
        lblProductName.setText(entreprise.getNom());
        
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

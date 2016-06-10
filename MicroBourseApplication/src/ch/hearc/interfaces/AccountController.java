/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.interfaces;

import ch.hearc.daoimplement.HistoriqueActionnaireDaoImplement;
import ch.hearc.metiers.HistoriqueActionnaire;
import ch.hearc.servicesdao.ServicesHistoriqueActionnaireDao;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 */
public class AccountController implements Initializable {

    @FXML
    private Label tableRequests;
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private Label lblInitCapital;
    @FXML
    private Label lblActualCapital;
    @FXML
    private TableView<HistoriqueModel> tableSale;
    @FXML
    private TableColumn<HistoriqueModel, String> unitPriceSaleCol;
    @FXML
    private TableColumn<HistoriqueModel, String> nbSaleActionCol;
    @FXML
    private TableColumn<HistoriqueModel, String> priceSaleCol;
    @FXML
    private TableColumn<HistoriqueModel, String> productSaleCol;
    @FXML
    private TableView<HistoriqueModel> tableBuy;
    @FXML
    private TableColumn<HistoriqueModel, String> unitPriceBuyCol;
    @FXML
    private TableColumn<HistoriqueModel, String> nbBuyActionCol;
    @FXML
    private TableColumn<HistoriqueModel, String> priceBuyCol;
    @FXML
    private TableColumn<HistoriqueModel, String> productBuyCol;

    private long ID;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart.setTitle("Mon Capital");
        showChart();
    }
    
    /**
     * Show the graph
     */
    public void showChart() {
        ServicesHistoriqueActionnaireDao soo = new HistoriqueActionnaireDaoImplement();
        List<HistoriqueActionnaire> capitalHisto = soo.getHistoriqueActionnaire(AppController.getIdUser());

        XYChart.Series capitalSeries = new XYChart.Series();
        capitalSeries.setName("Capital");

        for(HistoriqueActionnaire ha : capitalHisto)
            capitalSeries.getData().add(new XYChart.Data(ha.getDate().toString(), ha.getCapital()));

        // Affichage de la série
        chart.getData().addAll(capitalSeries);
    }
    
    /**
     * 
     * @param id 
     */
    public void setID(long id) {
        this.ID = id;
    }
    
    /**
     * 
     */
    public void showTables() {
//        ServicesOffreDao soo = new OffreDaoImplement();
//        List<Offre> offresVente = soo.getHistoriqueActionnaire(this.ID);
//        List<Offre> offresAchat = soo.getCurrentPurchaseOffersByEntreprise(this.ID);
//
//        ObservableList<HistoriqueModel> achats = FXCollections.observableArrayList();
//        for (Offre offre : offresAchat) {
//            achats.add(new HistoriqueModel(offre));
//        }
//        
//        ObservableList<HistoriqueModel> ventes = FXCollections.observableArrayList();
//        for (Offre offre : offresVente) {
//            ventes.add(new HistoriqueModel(offre));
//        }
//        
//        buyPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
//        nbBuyActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
//        
//        salePriceCol.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
//        nbSaleActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
//                
//        tableBuy.setItems(achats);
//        tableSale.setItems(ventes);
    }
}
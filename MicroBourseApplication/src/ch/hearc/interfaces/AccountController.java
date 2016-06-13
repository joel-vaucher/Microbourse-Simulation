/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.interfaces;

import ch.hearc.daoimplement.HistoriqueActionnaireDaoImplement;
import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.HistoriqueActionnaire;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesHistoriqueActionnaireDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 */
public class AccountController implements Initializable {

    @FXML
    private LineChart<String, Number> chart;
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
    private TableColumn<HistoriqueModel, String> entrepriseSaleCol;
    @FXML
    private TableView<HistoriqueModel> tableBuy;
    @FXML
    private TableColumn<HistoriqueModel, String> unitPriceBuyCol;
    @FXML
    private TableColumn<HistoriqueModel, String> nbBuyActionCol;
    @FXML
    private TableColumn<HistoriqueModel, String> priceBuyCol;
    @FXML
    private TableColumn<HistoriqueModel, String> entrepriseBuyCol;
    
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
        Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    showChart();
                    showTables();
                });
            }
        }, 0, 60000);
    }
    
    /**
     * Show the graph
     */
    public void showChart() {
        ServicesHistoriqueActionnaireDao sha = new HistoriqueActionnaireDaoImplement();
        List<HistoriqueActionnaire> capitalHisto = sha.getHistoriqueActionnaire(AppController.getIdUser());

        chart.getData().clear();
        
        XYChart.Series capitalSeries = new XYChart.Series();
        capitalSeries.setName("Capital");

        for(HistoriqueActionnaire ha : capitalHisto)
            capitalSeries.getData().add(new XYChart.Data(ha.getDate().toString(), ha.getCapital()));

        // Affichage de la série
        chart.getData().addAll(capitalSeries);
        
        if(!capitalHisto.isEmpty()) {
            // Affichage du capital actuel
            lblActualCapital.setText(capitalHisto.get(capitalHisto.size()-1).getCapital()+"");
        }
    }
    
    /**
     * 
     */
    public void showTables() {
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> histoOffreVente = soo.getHistoSellOffersByActionnaire(AppController.getIdUser());
        List<Offre> histoOffreAchat = soo.getHistoPurchaseOffersByActionnaire(AppController.getIdUser());
        
        ObservableList<HistoriqueModel> achats = FXCollections.observableArrayList();
        for (Offre offre : histoOffreAchat) {
            achats.add(new HistoriqueModel(offre));
        }
        
        ObservableList<HistoriqueModel> ventes = FXCollections.observableArrayList();
        for (Offre offre : histoOffreVente) {
            ventes.add(new HistoriqueModel(offre));
        }
        
        unitPriceBuyCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        nbBuyActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
        priceBuyCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        entrepriseBuyCol.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
          
        unitPriceSaleCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        nbSaleActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
        priceSaleCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        entrepriseSaleCol.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        
        tableBuy.setItems(achats);
        tableSale.setItems(ventes);
    }
    
}
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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 * Classe permettant de controller le composant graphique "Account"
 * Ce composant permet d'afficher le compte de l'utilisateur
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
        chart.setTitle("Mon Capital");
        // Lancement du timer de rafraichissement
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
     * Récupération et traitemement des données reçues depuis la BDD
     * afin de créer la courbe affichée sur le graphique
     */
    public void showChart() {
        // Récupération des données
        ServicesHistoriqueActionnaireDao sha = new HistoriqueActionnaireDaoImplement();
        List<HistoriqueActionnaire> capitalHisto = sha.getHistoriqueActionnaire(AppController.getUserID());

        // Nettoyage du graphique
        chart.getData().clear();
        
        // Création de la courbe
        XYChart.Series capitalSeries = new XYChart.Series();
        capitalSeries.setName("Capital");

        for(HistoriqueActionnaire ha : capitalHisto)
            capitalSeries.getData().add(new XYChart.Data(ha.getDate().toString(), ha.getCapital()));

        // Affectation de la courbe au graphique
        chart.getData().addAll(capitalSeries);
        
        // Affichage du capital actuel
        if(!capitalHisto.isEmpty()) {
            lblActualCapital.setText(capitalHisto.get(capitalHisto.size()-1).getCapital()+"");
        }
    }
    
    /**
     * Récupération et traitemement des données reçues depuis la BDD
     * afin de peupler les tableaux.
     */
    public void showTables() {
        // Récupération des données
        ServicesOffreDao soo = new OffreDaoImplement();
        List<Offre> histoOffreVente = soo.getHistoSellOffersByActionnaire(AppController.getUserID());
        List<Offre> histoOffreAchat = soo.getHistoPurchaseOffersByActionnaire(AppController.getUserID());
        
        // Nettoyage des tableaux
        tableBuy.getItems().clear();
        tableSale.getItems().clear();
        
        // Préparation des données à l'aide d'un modèle
        ObservableList<HistoriqueModel> achats = FXCollections.observableArrayList();
        for (Offre offre : histoOffreAchat) {
            achats.add(new HistoriqueModel(offre));
        }
        
        ObservableList<HistoriqueModel> ventes = FXCollections.observableArrayList();
        for (Offre offre : histoOffreVente) {
            ventes.add(new HistoriqueModel(offre));
        }
        
        // Association des attributs du modèle aux colonnes du tableau
        unitPriceBuyCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        nbBuyActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
        priceBuyCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        entrepriseBuyCol.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
          
        unitPriceSaleCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        nbSaleActionCol.setCellValueFactory(new PropertyValueFactory<>("nbActions"));
        priceSaleCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        entrepriseSaleCol.setCellValueFactory(new PropertyValueFactory<>("entreprise"));
        
        // Peuplement des tableaux
        tableBuy.setItems(achats);
        tableSale.setItems(ventes);
    }
    
}
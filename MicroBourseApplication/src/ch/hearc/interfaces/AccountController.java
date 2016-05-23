/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 */
public class AccountController implements Initializable {

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
    private TableView<?> tableOffers;
    @FXML
    private Label tableRequests;
    @FXML
    private LineChart<?, ?> chart;

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
    
}

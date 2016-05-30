/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.interfaces;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 */
public class AccountController implements Initializable {

    @FXML
    private TableView<?> tableOffers;
    @FXML
    private Label tableRequests;
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private Label lblInitCapital;
    @FXML
    private Label lblActualCapital;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chart.setTitle("Capital");
    }      
}
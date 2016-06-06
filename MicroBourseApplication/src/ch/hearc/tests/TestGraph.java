package ch.hearc.tests;

import ch.hearc.metiers.Offre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TestGraph extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Test Graph");
        stage.setScene(makeScene());
        stage.show();
    }
    
    
    public Scene makeScene() {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
        
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Prix Offre");
        //populating the series with data
        ArrayList<Offre> list = listOffre();
        int i = 0;
        for (Offre next : list) {   
            series.getData().add(new XYChart.Data(i++, next.getPrix()));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        
        return scene;
    }
    
    public ArrayList<Offre> listOffre() {
        ArrayList<Offre> listOffre = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Offre o = new Offre(Long.parseLong("123"));
            o.setPrix(new Random().nextDouble());
            listOffre.add(o);
        }
        return listOffre;
    }
    
}

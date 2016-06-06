package ch.hearc;

import ch.hearc.interfaces.AppController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        showApp(selectUser(), primaryStage);
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private Long selectUser() {

        return 10L;
    }
    
    private void showApp(Long ID , Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ch/hearc/interfaces/App.fxml"));
        AnchorPane app = null;
        try {
            app = (AnchorPane) loader.load();
            AppController appController = loader.getController();
            appController.setID(ID);
        } catch (IOException ex) {
            ex.getMessage();
        } finally {                   
            Scene scene = new Scene(app);
            
            primaryStage.setMaxHeight(800.0);
            primaryStage.setMaxWidth(1280.0);
            primaryStage.setTitle("Minibourse");
            //stage.getIcons().add(new Image(""));
            primaryStage.setScene(scene); 
            primaryStage.show();
        }
    }
}
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
//        StackPane sp = null;
//        try {
//            sp = (StackPane) loader.load();
//            DetailController detail = loader.getController();
//            detail.setID(ID);
//            AppController.viewDetails(sp);
//        } catch (IOException ex) {
//           Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
//        } 

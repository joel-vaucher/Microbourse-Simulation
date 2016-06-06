package ch.hearc.tests;

import ch.hearc.daoimplement.ActionnaireDaoImplement;
import ch.hearc.exception.DatabaseException;
import ch.hearc.interfaces.TuileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;



/**
 *
 * @author charlesombangndo
 */
public class TestInterface extends Application{
    
    /**
     * 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ch/hearc/interfaces/App.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setTitle("Minibourse");
        //stage.getIcons().add(new Image(""));
        stage.setScene(scene);
        stage.show();
    }    
    /**
     * @param args the command line arguments
     * @throws ch.hearc.exception.DatabaseException
     */
    public static void main(String[] args) throws DatabaseException {
        launch(args);
        ActionnaireDaoImplement test1 = new ActionnaireDaoImplement();
       
        //test1.getQuantiteAction(1L,1L);
    }
}

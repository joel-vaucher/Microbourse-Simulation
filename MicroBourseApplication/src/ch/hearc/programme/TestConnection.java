package ch.hearc.programme;

import ch.hearc.daoimplement.ActionnaireDaoImplement;
import ch.hearc.exception.DatabaseException;



/**
 *
 * @author charlesombangndo
 */
public class TestConnection extends Application{
    
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
        ActionnaireDaoImplement test1 = new ActionnaireDaoImplement();
        
        
        test1.getQuantiteAction(1L,1L);
    }
}

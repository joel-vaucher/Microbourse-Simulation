package ch.hearc.programme;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.utilitaires.CreateDataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author charlesombangndo
 */
public class Test extends Application{
    
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
        Connection conn;
        Statement state;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();           
            
            ResultSet result = state.executeQuery("SELECT * FROM BEBE WHERE ID <= 7");
            while (result.next()) {
                Long id = result.getLong("ID");
                String name = result.getString("NOM");
                System.out.println(id + "\t" + name);
            }
        }catch(SQLException ex){
            ex.getMessage();
        }
        //Test réussi
        CreateDataBase creation = new CreateDataBase();
        creation.createTable();
        
        launch(args);
    }
}

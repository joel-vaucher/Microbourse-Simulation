package ch.hearc.programme;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.utilitaires.CreateDataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author charlesombangndo
 */
public class TestConnection {
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
    }
}

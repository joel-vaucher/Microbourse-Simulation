package ch.hearc.utilitaires;

import ch.hearc.databasefactory.DataBaseConnection;
import java.sql.Connection;
import java.sql.Statement;
import ch.hearc.exception.DatabaseException;
import java.sql.SQLException;

/**
 *Création de la base de donnée depuis le programme JAVA
 * @author charlesombangndo
 */
public class CreateDataBase{
    
    public CreateDataBase(){
        
    }
    
    public void createTable() throws DatabaseException{
        Connection conn;
        Statement state;
        //StringBuilder sb = new StringBuilder();
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();   
            String sql = "CREATE TABLE REGISTRATION " +
                    "(id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    " name VARCHAR(255) NOT NULL, " + 
                    " last_name VARCHAR(10), " + 
                    " age INTEGER)"; 
            state.executeUpdate(sql);
            conn.commit();
            System.out.println("La table est bien créée dans la base de donnée spécifiée...");
        }catch(SQLException ex){
            ex.getMessage();
        }
    }
}

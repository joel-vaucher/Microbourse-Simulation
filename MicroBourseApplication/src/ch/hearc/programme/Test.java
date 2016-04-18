/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.programme;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author charlesombangndo
 */
public class Test {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DatabaseException {
        Connection conn;
        Statement state = null;
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
    }
    
}

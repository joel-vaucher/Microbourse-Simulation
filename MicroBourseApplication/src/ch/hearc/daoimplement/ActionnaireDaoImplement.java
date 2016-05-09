/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.daoimplement;
import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;

import ch.hearc.servicesdao.ServicesActionnaireDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author charlesombangndo
 */
public class ActionnaireDaoImplement implements ServicesActionnaireDAO{

    public ActionnaireDaoImplement(){
        
    }
    
    /**
     *
     * @param id1
     * @param id2
     * @throws DatabaseException
     */
    @Override
    public void getQuantiteAction(Long id1,Long id2) throws DatabaseException {
        
        Statement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            String query = "SELECT QUANTITE FROM ACTIONS INNER JOIN ACTIONNAIRES ON 1=1";//+id1+"="+id2;
            result = state.executeQuery(query);
            while(result.next()){
                int quantite = result.getInt(2);
                System.out.println(quantite);
            }
        }catch(SQLException ex){
            ex.getMessage();
          
    }
    }

    @Override
    public Actionnaire getActionnaireByID() {
        //TODO
        return new Actionnaire(Integer.toUnsignedLong(1), "Max");
    }

    @Override
    public Actionnaire getActionnaireByID() {
        //TODO
        return new Actionnaire(Integer.toUnsignedLong(1), "Max");
    }
}
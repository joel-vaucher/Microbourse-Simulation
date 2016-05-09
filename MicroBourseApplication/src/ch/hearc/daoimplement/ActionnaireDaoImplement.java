/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.daoimplement;
import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Action;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.servicesdao.ServicesActionnaireDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author charlesombangndo
 */
public class ActionnaireDaoImplement implements ServicesActionnaireDAO{

    public ActionnaireDaoImplement(){
        
    }
    
    @Override
    public int getQuantiteAction(Actionnaire actionnaire,Action action) throws DatabaseException{
        
        PreparedStatement prepare = null;
        Connection conn = null;
        ResultSet result = null;
        int quantite = 0;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            
            String query = "SELECT QUANTITE FROM ACTION INNER JOIN ACTIONNAIRES ON ? = ?";
            
            prepare.setLong(1, action.getIdACtion());
            prepare.setLong(2, actionnaire.getIdActionnaire());
            prepare = conn.prepareStatement(query);
            
            result = prepare.executeQuery();
            
            while (result.next()) {
                quantite = result.getInt("QUANTITE");
            }
            //return quantite;
        }catch(SQLException ex){
            ex.getMessage();
          
    }
    return quantite;
    }

    @Override
    public Actionnaire getActionnaireByID() {
        //TODO
        return new Actionnaire(Integer.toUnsignedLong(1), "Max");
    }
}

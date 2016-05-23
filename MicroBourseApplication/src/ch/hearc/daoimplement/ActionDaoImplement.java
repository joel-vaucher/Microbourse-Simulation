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
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesActionDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesombangndo
 */
public class ActionDaoImplement implements ServicesActionDAO{

    public ActionDaoImplement(){
        
    }
    
    @Override
    public void createAction(Action action) {
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = "INSERT INTO Actions(quantite, fk_actionnaire_2, fk_entreprise) VALUES(?,?,?)";
            state = conn.prepareStatement(query, new String[]{"ID"});
            state.setInt(1, action.getQuantite());
            state.setLong(2, action.getIdActionnaire());
            state.setLong(3, action.getIdEntreprise());
            
            
            state.executeUpdate();
            DataBaseConnection.getDataBase().commit();
            ResultSet rs = state.getGeneratedKeys();
            rs.next();
            Long lastId = rs.getLong(1);
            action.setIdAction(lastId);
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Action getActionByID() {
        //TODO
        return null;
    }

    @Override
    public void updateAction(Action acVendeur) {
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = "UPDATE Actions SET quantite=?, fk_actionnaire_2=?, fk_entreprise=? WHERE id = ?";
            state = conn.prepareStatement(query);
            state.setInt(1, acVendeur.getQuantite());
            state.setLong(2, acVendeur.getIdActionnaire());
            state.setLong(3, acVendeur.getIdEntreprise());
            state.setLong(4, acVendeur.getIdAction());
            
            
            state.executeUpdate();
            DataBaseConnection.getDataBase().commit();
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Action getActionOfActionnaireByEnterprise(Long idA, Long idE) {
        
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        Action action = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = "SELECT * FROM actions WHERE fk_actionnaire_2 = ? AND fk_entreprise = ?";
            state = conn.prepareStatement(query);
            state.setLong(1, idA);
            state.setLong(2, idE);
            result = state.executeQuery();
            if(result.next()){  
                do{
                    Long id = result.getLong("ID");
                    int quantite = result.getInt("QUANTITE");
                    Long idActionnaire = result.getLong("FK_ACTIONNAIRE_2");
                    Long idEntreprise = result.getLong("FK_ENTREPRISE");
                    action = new Action(id,quantite,idEntreprise,idActionnaire);
                } while(result.next());
            } else {
                action = new Action(null, 0, idE, idA);
                createAction(action);
            }
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return action;
    }
}


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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesombangndo
 */
public class ActionnaireDaoImplement implements ServicesActionnaireDAO{

    public ActionnaireDaoImplement(){
        
    }

    @Override
    public Actionnaire getActionnaireByID(Long idA) {
        
        Statement state = null;
        Connection conn = null;
        ResultSet result = null;
        Actionnaire actionnaire = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            String query = "SELECT ID FORM ACTONNAIRES WHERE ID = idA";
            result = state.executeQuery(query);
            
            while(result.next()){
                Long idActionnaire = result.getLong("ID");
                String nom = result.getString("NOM");
                double capital = result.getLong("CAPITAL");
                actionnaire = new Actionnaire(idActionnaire, nom,capital);
            }     
        }catch(SQLException ex){
            ex.getMessage();
        }catch(DatabaseException ex){
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actionnaire;
    }

    @Override
    public int getQuantiteAction(Actionnaire actionnaire, Action action) throws DatabaseException {
        
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
        return 0;
    }

    @Override
    public void updateActionnaire(Actionnaire actionnaire) {
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = "UPDATE Actionnaires SET nom=?, capital=? WHERE id = ?";
            state.setString(1, actionnaire.getNom());
            state.setDouble(2, actionnaire.getCapital());
            state.setLong(3, actionnaire.getIdActionnaire());
            
            state.executeUpdate();
            DataBaseConnection.getDataBase().commit();
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

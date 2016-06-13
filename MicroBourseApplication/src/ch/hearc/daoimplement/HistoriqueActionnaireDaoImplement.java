package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.HistoriqueActionnaire;
import ch.hearc.servicesdao.ServicesHistoriqueActionnaireDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author charlesombangndo
 */
public class HistoriqueActionnaireDaoImplement implements ServicesHistoriqueActionnaireDao{
    
    @Override
    public synchronized void createHistoriqueActionnaire(HistoriqueActionnaire h) {
        
        Statement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            String query = String.format("INSERT INTO HistoriquesActionnaires(capital,date_histo,fk_actionnaire_3) VALUES(%1$f,\'%2$s\',%3$d)",
                    h.getCapital(),
                    h.getDate(),
                    h.getIdActionnaire());
            
            state.executeUpdate(query, new String[]{"ID"});
            DataBaseConnection.getDataBase().commit();
            ResultSet rs = state.getGeneratedKeys();
            rs.next();
            Long lastId = rs.getLong(1);
            h.setIdHisto(lastId);
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(HistoriqueActionnaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void createHistoriqueActionnaire(Actionnaire a) {
        HistoriqueActionnaire h = new HistoriqueActionnaire(null, a.getCapital(), new Date(System.currentTimeMillis()),a.getIdActionnaire());
        
        createHistoriqueActionnaire(h);
    }
    public static ServicesHistoriqueActionnaireDao getWritingIntance() {
        return writingInstance;
    }
    
    private static ServicesHistoriqueActionnaireDao writingInstance = new HistoriqueActionnaireDaoImplement();

    @Override
    public List<HistoriqueActionnaire> getHistoriqueActionnaire(Long idActionnaire) {
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet rs = null;
        List<HistoriqueActionnaire> listHisto = new ArrayList<>();
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = String.format("SELECT * FROM historiquesactionnaires ha WHERE id=? ORDER BY ha.date_HISTO ASC");
            state = conn.prepareStatement(query, new String[]{"ID"});
            state.setLong(1, idActionnaire);
            
            rs = state.executeQuery();
            
            while(rs.next()) {
                HistoriqueActionnaire ha;
                Long idHisto = rs.getLong("ID");
                Double capital = rs.getDouble("CAPITAL");
                Date date = rs.getDate("DATE_HISTO");
                ha = new HistoriqueActionnaire(idHisto, capital, date, idActionnaire);
                listHisto.add(ha);
            }
            
        }catch(SQLException | DatabaseException ex){
            ex.getMessage();
        }
        
        return listHisto;
    }
}

package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.HistoriqueActionnaire;
import ch.hearc.servicesdao.ServicesHistoriqueActionnaireDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author charlesombangndo
 */
public class HistoriqueActionnaireDaoImplement implements ServicesHistoriqueActionnaireDao{

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
            String query = "SELECT capital, date_histo FROM historiquesactionnaires WHERE fk_actionnaire = ?";
            state = conn.prepareStatement(query, new String[]{"ID"});
            state.setLong(1, idActionnaire);
            
            rs = state.executeQuery();
            
            while(rs.next()) {
                HistoriqueActionnaire ha;
                Double capital = rs.getDouble("CAPITAL");
                Date date = rs.getDate("DATE_HISTO");
                ha = new HistoriqueActionnaire(capital, date);
                listHisto.add(ha);
            }
            
        }catch(SQLException | DatabaseException ex){
            ex.getMessage();
        }
        
        return listHisto;
    }
}

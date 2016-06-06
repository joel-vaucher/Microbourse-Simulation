package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import ch.hearc.servicesdao.ServicesHistoriqueEntrepriseDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesombangndo
 */
public class HistoriqueEntrepriseDaoImplement implements ServicesHistoriqueEntrepriseDao{

    @Override
    public synchronized void createHistoriqueEntreprise(HistoriqueEntreprise h) {
        
        Statement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            String query = String.format("INSERT INTO HistoriquesEntreprises(quantite_ressource, quantite_res_vente_totale, capital, capital_vente_total, date_histo, fk_historique_entre) VALUES(%1$d,%2$d,%3$f,%4$f,\'%s\',%6$d)",
                    h.getQuantiteRessource(),
                    h.getQuantiteRessourceVenteTotal(),
                    h.getCapital(),
                    h.getCapitalVenteTotal(),
                    h.getDate(),
                    h.getIdEntreprise());
            
            state.executeUpdate(query, new String[]{"ID"});
            DataBaseConnection.getDataBase().commit();
            ResultSet rs = state.getGeneratedKeys();
            rs.next();
            Long lastId = rs.getLong(1);
            h.setIdHisto(lastId);
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(HistoriqueEntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void createHistoriqueEntreprise(Entreprise e) {
        HistoriqueEntreprise h = new HistoriqueEntreprise(null, e.getQuantiteRessource(), e.getQuantiteRessourceVenteTotal(), e.getCapital(),
                                                        e.getCapitalVenteTotal(), new Date(System.currentTimeMillis()),e.getIdEntreprise());
        
        createHistoriqueEntreprise(h);
    }

    public static ServicesHistoriqueEntrepriseDao getWritingIntance() {
        return writingInstance;
    }
    
    private static ServicesHistoriqueEntrepriseDao writingInstance = new HistoriqueEntrepriseDaoImplement();
}

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesombangndo
 */
public class HistoriqueEntrepriseDaoImplement implements ServicesHistoriqueEntrepriseDao{

    @Override
    public synchronized void createHistoriqueEntreprise(HistoriqueEntreprise h) {
        
        PreparedStatement state = null;
        Connection conn = null;
        ResultSet result = null;
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = "INSERT INTO HistoriquesEntreprises(quantite_ressource, quantite_res_vente_totale, capital, capital_vente_total, date_histo, fk_historique_entre) VALUES(?,?,?,?,?,?)";
            state = conn.prepareStatement(query, new String[]{"ID"});
            state.setInt(1, h.getQuantiteRessource());
            state.setInt(2, h.getQuantiteRessourceVenteTotal());
            state.setDouble(3, h.getCapital());
            state.setDouble(4, h.getCapitalVenteTotal());
            state.setDate(5, h.getDate());
            state.setLong(6, h.getIdEntreprise());
            
            state.executeUpdate();
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

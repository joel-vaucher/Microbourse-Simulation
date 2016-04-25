package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesombangndo
 */
public class EntrepriseDaoImplement implements ServicesEntrepriseDao {

    @Override
    public double StockActuel(Entreprise entreprise) {
        return (double) entreprise.getQuantiteRessource() / ConsommationMensuelle(entreprise);
    }

    @Override
    public double ConsommationMensuelle(Entreprise entreprise) {
        return 0;
    }

    @Override
    public List<HistoriqueEntreprise> getHistoriqueOf(Entreprise entreprise) {

        Connection conn;
        Statement state = null;
        List<HistoriqueEntreprise> historiqueList;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            historiqueList = new ArrayList<HistoriqueEntreprise>(30);
            ResultSet result = state.executeQuery(String.format("SELECT * FROM HistoriqueEntreprise WHERE fk_Entreprise = %d", entreprise.getIdEntreprise()));
            while (result.next()) {
                Long id = result.getLong("ID");
                Date date = result.getDate("DATE");
                int quantite = result.getInt("QUANTITE_RESSOURCE");
                
                historiqueList.add(new HistoriqueEntreprise(id, date, quantite, entreprise));
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return null;
    }
    
    @Override
    public Entreprise getEntrepriseByID(Long idEntreprise) {
        return null;
    }

}

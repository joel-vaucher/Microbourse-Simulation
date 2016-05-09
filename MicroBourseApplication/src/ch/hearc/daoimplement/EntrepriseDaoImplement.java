package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Capital;
import ch.hearc.metiers.Consommateur;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.metiers.Producteur;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
        return ((double) entreprise.getQuantiteRessource()) / ConsommationMensuelle(entreprise);
    }

    @Override
    public double ConsommationMensuelle(Entreprise entreprise) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        Date lastMonth = new Date(c.get(Calendar.LONG));
        HistoriqueEntreprise h = getNearestHistorique(entreprise,lastMonth);
        return entreprise.getQuantiteRessource() - h.getQuantiteRessource();
    }


    @Override
    public HistoriqueEntreprise getNearestHistorique(Entreprise entreprise, Date nearDate) {
        Connection conn;
        Statement state = null;
        HistoriqueEntreprise historiqueList = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            ResultSet result = state.executeQuery(String.format("SELECT * FROM HISTORIQUESENTREPRISES WHERE fk_Entreprise = %d  AND date < \'%s\' ORDER BY date", entreprise.getIdEntreprise(), nearDate.toString()));
            result.next();
            Long id = result.getLong("ID");
            Date date = result.getDate("DATE");
            int quantite = result.getInt("QUANTITE_RESSOURCE");

            historiqueList = new HistoriqueEntreprise(id, date, quantite, entreprise);
            
        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return historiqueList;
    }
    
    @Override
    public List<HistoriqueEntreprise> getHistoriqueOf(Entreprise entreprise) {

        Connection conn;
        Statement state = null;
        List<HistoriqueEntreprise> historiqueList = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            historiqueList = new ArrayList<HistoriqueEntreprise>(30);
            ResultSet result = state.executeQuery(String.format("SELECT * FROM HistoriqueEntreprises WHERE fk_Entreprise = %d", entreprise.getIdEntreprise()));
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
        return historiqueList;
    }
    
    @Override
    public Entreprise getEntrepriseByID(Long idEntreprise) {
        return null;
    }

    @Override
    public List<Entreprise> getEntreprises() {
        Connection conn;
        Statement state = null;
        List<Entreprise> entreprises = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            entreprises = new ArrayList<Entreprise>();
            ResultSet result = state.executeQuery(String.format("SELECT * FROM Entreprises"));
            while (result.next()) {
                Long idEntreprise = result.getLong("ID");
                String nom  = result.getString("NOM");
                int quantiteRessource  = result.getInt("QUANTITE_RESSOURCE");
                
                //TODO obtenir List des objets en fonction des id
                //donc créer les manager pour capitaux, produteur, consommateur
                //genre
                // = getCapitauxByEntreprise(result.getLong("fk_entreprise");
                List<Capital> listeCapitaux = null;
                List<Producteur> listeProducteur = null;
                List<Consommateur> listeConsommateur = null;
                
                entreprises.add(new Entreprise(idEntreprise, nom, quantiteRessource, listeCapitaux,listeProducteur,listeConsommateur));
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return entreprises;
    }

}

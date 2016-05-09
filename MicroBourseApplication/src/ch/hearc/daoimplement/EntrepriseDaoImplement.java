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
    public Entreprise getEntrepriseByID(Long idE) {
        Connection conn;
        Statement state = null;
        Entreprise entreprise = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            ResultSet result = state.executeQuery(String.format("SELECT * FROM Entreprises"));

            result.next();

            Long id = result.getLong("ID");
            String nom = result.getString("NOM");
            int quantite = result.getInt("QUANTITE_RESSOURCE");
            int quantiteRessourceVenteTotal = result.getInt("QUANTITE_RES_VENTE_TOTALE");
            double capital = result.getDouble("CAPITAL");
            double capitalVenteTotal = result.getInt("CAPITAL_VENTE_TOTAL");

            entreprise = new Entreprise(id, nom, quantite, quantiteRessourceVenteTotal, capital, capitalVenteTotal);

        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return entreprise;
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
                Long id = result.getLong("ID");
                String nom = result.getString("NOM");
                int quantite = result.getInt("QUANTITE_RESSOURCE");
                int quantiteRessourceVenteTotal = result.getInt("QUANTITE_RES_VENTE_TOTALE");
                double capital = result.getDouble("CAPITAL");
                double capitalVenteTotal = result.getInt("CAPITAL_VENTE_TOTAL");

                entreprises.add(new Entreprise(id, nom, quantite, quantiteRessourceVenteTotal, capital, capitalVenteTotal));
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return entreprises;
    }

    @Override
    public double StockActuel(Long idE) {
        Entreprise e = getEntrepriseByID(idE);
        return ((double) e.getQuantiteRessource()) / ConsommationMensuelle(idE);
    }

    @Override
    public double ConsommationMensuelle(Long idE) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        Date lastMonth = new Date(c.get(Calendar.LONG));
        Entreprise e = getEntrepriseByID(idE);
        HistoriqueEntreprise h = getNearestHistorique(idE, lastMonth);
        return e.getQuantiteRessourceVenteTotal() - h.getQuantiteRessourceVenteTotal();
    }

    @Override
    public HistoriqueEntreprise getNearestHistorique(Long idE, Date nearDate) {
        Connection conn;
        Statement state = null;
        HistoriqueEntreprise historiqueList = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            ResultSet result = state.executeQuery(String.format("SELECT * FROM HISTORIQUESENTREPRISES WHERE fk_Entreprise = %d  AND date < \'%s\' ORDER BY date", idE, nearDate.toString()));
            result.next();
            Long id = result.getLong("ID");
            Date date = result.getDate("DATE_HISTO");
            int quantite = result.getInt("QUANTITE_RESSOURCE");
            int quantiteRessourceVenteTotal = result.getInt("QUANTITE_RES_VENTE_TOTALE");
            double capital = result.getDouble("CAPITAL");
            double capitalVenteTotal = result.getInt("CAPITAL_VENTE_TOTAL");

            historiqueList = new HistoriqueEntreprise(id, quantite, quantiteRessourceVenteTotal, capital, capitalVenteTotal, date, idE);

        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return historiqueList;
    }

    @Override
    public List<HistoriqueEntreprise> getHistoriqueOf(Long idE) {

        Connection conn;
        Statement state = null;
        List<HistoriqueEntreprise> historiqueList = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            historiqueList = new ArrayList<HistoriqueEntreprise>(30);
            ResultSet result = state.executeQuery(String.format("SELECT * FROM HistoriqueEntreprises WHERE fk_Entreprise = %d", idE));
            while (result.next()) {
                Long id = result.getLong("ID");
                Date date = result.getDate("DATE_HISTO");
                int quantite = result.getInt("QUANTITE_RESSOURCE");
                int quantiteRessourceVenteTotal = result.getInt("QUANTITE_RES_VENTE_TOTALE");
                double capital = result.getDouble("CAPITAL");
                double capitalVenteTotal = result.getInt("CAPITAL_VENTE_TOTAL");

                historiqueList.add(new HistoriqueEntreprise(id, quantite, quantiteRessourceVenteTotal, capital, capitalVenteTotal, date, idE));
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return historiqueList;
    }

}

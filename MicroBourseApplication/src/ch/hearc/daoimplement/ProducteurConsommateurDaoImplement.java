package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesProducteurConsommateurDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesombangndo
 */
public class ProducteurConsommateurDaoImplement implements ServicesProducteurConsommateurDao{

    @Override
    public void vendreRessource(Long idProd) {
        EntrepriseDaoImplement managerEntreprise = new EntrepriseDaoImplement();
        Entreprise entreprise = getEntrepriseById(idProd);
        double stockActuel = managerEntreprise.StockActuel(entreprise.getIdEntreprise());  //nbre mois avant épuisement des stock
        int quantiteVendu = 50;
        double coeffPrix = (double)quantiteVendu/(quantiteVendu+entreprise.getQuantiteRessource());
        coeffPrix *= 1/(1+stockActuel/10);
        
        //double prixunitaire = coeffPrix * entreprise.capitalSuisse/quantiteVendu;
        //mise à jour des compteur
        //entreprise.addStatMensuelle(quantiteVendu, prixunitaire);
        //entreprise.prixUnitairePlafond = prixunitaire*10;
        
        
        //résolution de la vente
        //entreprise.capitalSuisse -= quantiteVendu * prixunitaire;
        //entreprise.quantiteRessource += quantiteVendu;
            
        //System.out.printf("Client %d - QV:%d | QR:%d | PU:%f | C:%f\n",entreprise.getIdEntreprise(),quantiteVendu,entreprise.getQuantiteRessource(),prixunitaire,entreprise.capitalSuisse);
    }

    @Override
    public void acheterRessource(Long idCons) {
        EntrepriseDaoImplement managerEntreprise = new EntrepriseDaoImplement();
        Entreprise entreprise = getEntrepriseById(idCons);
        /*double prixunitaire = entreprise.prixUnitaireMensuelle;
        double stockActuel = entreprise.quantiteRessource/entreprise.consMensuelle;  //nbre mois avant épuisement des stock
        prixunitaire /= stockActuel/entreprise.stockIdeal;    // trop de stock signifie un prix moins cher, pas assez augmente le prix, peut aller de 0 à PrixUnitairePlafond
        if(prixunitaire > entreprise.prixUnitairePlafond){
            prixunitaire = entreprise.prixUnitairePlafond;
        }
        double coeffAchat = 1.2/(prixunitaire/5+1);   //doit être au maximum à 1.2 et tendre vers 0
        int quantiteAcheter = (int)(coeffAchat*(double)entreprise.quantiteRessource);
        
        //mise à jour des compteur
        entreprise.addStatMensuelle(quantiteAcheter, prixunitaire);
        entreprise.prixUnitairePlafond = prixunitaire*10;
        
        
        //résolution de l'achat
        if(quantiteAcheter > entreprise.quantiteRessource){
            entreprise.capitalSuisse += entreprise.quantiteRessource * prixunitaire;
            entreprise.quantiteRessource = 0;
        } else {
            entreprise.capitalSuisse += quantiteAcheter * prixunitaire;
            entreprise.quantiteRessource -= quantiteAcheter;
        }
        System.out.printf("Client %d - QA:%d | QR:%d | PU:%f | C:%f\n",client,quantiteAcheter,entreprise.quantiteRessource,prixunitaire,entreprise.capitalSuisse);
        */
    }
    
    @Override
    public Entreprise getEntrepriseById(Long idProdCons){
        Connection conn;
        Statement state = null;
        Entreprise entreprise = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            ResultSet result = state.executeQuery(String.format("SELECT * FROM PRODUCTEURCONSOMMATEUR WHERE id = %d", idProdCons));
            result.next();
            Long idEntreprise = result.getLong("FK_ENTREPRISE");
            
            EntrepriseDaoImplement manager = new EntrepriseDaoImplement();
            entreprise = manager.getEntrepriseByID(idEntreprise);
            
        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return entreprise;
    }
    
/*
    @Override
    public void vendreRessource(Producteur prod) {
        double stockActuel = (double);  //nbre mois avant épuisement des stock
        int quantiteVendu = 50;
        double coeffPrix = (double)quantiteVendu/(quantiteVendu+entreprise.quantiteRessource);
        coeffPrix *= 1/(1+stockActuel/10);
        
        double prixunitaire = coeffPrix * entreprise.capitalSuisse/quantiteVendu;
        //mise à jour des compteur
        entreprise.addStatMensuelle(quantiteVendu, prixunitaire);
        entreprise.prixUnitairePlafond = prixunitaire*10;
        
        
        //résolution de la vente
        entreprise.capitalSuisse -= quantiteVendu * prixunitaire;
        entreprise.quantiteRessource += quantiteVendu;
            
        System.out.printf("Client %d - QV:%d | QR:%d | PU:%f | C:%f\n",client,quantiteVendu,entreprise.quantiteRessource,prixunitaire,entreprise.capitalSuisse);
    }

    @Override
    public void acheterRessource(Producteur cons) {
        double prixunitaire = entreprise.prixUnitaireMensuelle;
        double stockActuel = entreprise.quantiteRessource/entreprise.consMensuelle;  //nbre mois avant épuisement des stock
        prixunitaire /= stockActuel/entreprise.stockIdeal;    // trop de stock signifie un prix moins cher, pas assez augmente le prix, peut aller de 0 à PrixUnitairePlafond
        if(prixunitaire > entreprise.prixUnitairePlafond){
            prixunitaire = entreprise.prixUnitairePlafond;
        }
        double coeffAchat = 1.2/(prixunitaire/5+1);   //doit être au maximum à 1.2 et tendre vers 0
        int quantiteAcheter = (int)(coeffAchat*(double)entreprise.quantiteRessource);
        
        //mise à jour des compteur
        entreprise.addStatMensuelle(quantiteAcheter, prixunitaire);
        entreprise.prixUnitairePlafond = prixunitaire*10;
        
        
        //résolution de l'achat
        if(quantiteAcheter > entreprise.quantiteRessource){
            entreprise.capitalSuisse += entreprise.quantiteRessource * prixunitaire;
            entreprise.quantiteRessource = 0;
        } else {
            entreprise.capitalSuisse += quantiteAcheter * prixunitaire;
            entreprise.quantiteRessource -= quantiteAcheter;
        }
        System.out.printf("Client %d - QA:%d | QR:%d | PU:%f | C:%f\n",client,quantiteAcheter,entreprise.quantiteRessource,prixunitaire,entreprise.capitalSuisse);
    }
*/
}

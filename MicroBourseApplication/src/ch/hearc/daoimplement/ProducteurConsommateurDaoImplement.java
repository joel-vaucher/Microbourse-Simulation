package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.metiers.ProducteurConsommateur;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesProducteurConsommateurDao;
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
public class ProducteurConsommateurDaoImplement implements ServicesProducteurConsommateurDao{
    
    

    @Override
    public void vendreRessource(Long idProd) {
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        Entreprise entreprise = getEntrepriseById(idProd);
        ProducteurConsommateur prod = getProducteurConsommateurById(idProd);
        double stockActuel = seo.StockActuel(entreprise.getIdEntreprise());  //nbre mois avant épuisement des stock
        int quantiteVendu = prod.getQuantiteRessource();
        double coeffPrix = (double)quantiteVendu/(quantiteVendu+entreprise.getQuantiteRessource());
        coeffPrix *= 1/(1+stockActuel/10);
        
        double prixunitaire = coeffPrix * entreprise.getCapital()/quantiteVendu;
        
        //résolution de la vente
        seo.BuyRessource(entreprise.getIdEntreprise(), quantiteVendu, prixunitaire);
            
        System.out.printf("Client %d - QV:%d | QR:%d | PU:%f | C:%f | CT: %f | S: %f\n",entreprise.getIdEntreprise(),quantiteVendu,entreprise.getQuantiteRessource(),prixunitaire,entreprise.getCapital(), entreprise.getCapitalVenteTotal(), stockActuel);
    }

    @Override
    public void acheterRessource(Long idCons) {
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        Entreprise entreprise = getEntrepriseById(idCons);
        ProducteurConsommateur prod = getProducteurConsommateurById(idCons);
        double prixunitaire = entreprise.getCapitalVenteTotal()/entreprise.getQuantiteRessourceVenteTotal();
        double stockActuel = seo.StockActuel(entreprise.getIdEntreprise());  //nbre mois avant épuisement des stock
        prixunitaire /= stockActuel/2;    // trop de stock signifie un prix moins cher, pas assez augmente le prix, peut aller de 0 à PrixUnitairePlafond
        double coeffAchat = 1.2/(prixunitaire/5+1);   //doit être au maximum à 1.2 et tendre vers 0
        int quantiteAcheter = (int)(coeffAchat*(double)entreprise.getQuantiteRessource());        
        
        //résolution de l'achat
        seo.SellRessource(entreprise.getIdEntreprise(), quantiteAcheter, prixunitaire);
        
        System.out.printf("Client %d - QA:%d | QR:%d | PU:%f | C:%f | CT: %f | S: %f\n",entreprise.getIdEntreprise(),quantiteAcheter,entreprise.getQuantiteRessource(),prixunitaire,entreprise.getCapital(), entreprise.getCapitalVenteTotal(), stockActuel);
        
    }
    
    @Override
    public Entreprise getEntrepriseById(Long idProdCons){
        Connection conn;
        Statement state = null;
        Entreprise entreprise = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            ResultSet result = state.executeQuery(String.format("SELECT * FROM PRODUCTEURCONSOMMATEURS WHERE id = %d", idProdCons));
            result.next();
            Long idEntreprise = result.getLong("FK_ENTREPRISE_3");
            
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

    @Override
    public ProducteurConsommateur getProducteurConsommateurById(Long idProdCons) {
        
        Connection conn;
        PreparedStatement state = null;
        ProducteurConsommateur prodcons = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            String query = String.format("SELECT * FROM ProducteurConsommateurs WHERE id = ?");
            state = conn.prepareStatement(query);
            state.setLong(1, idProdCons);
            
            ResultSet result = state.executeQuery();
            result.next();
            
            Long id = result.getLong("ID");
            double fiabilite = result.getDouble("FIABILITE");
            int quantite = result.getInt("QUANTITE_RESSOURCE");
            Long idEntreprise = result.getLong("FK_ENTREPRISE_3");

            prodcons = new ProducteurConsommateur(id, ProducteurConsommateur.statusType.PRODUCTEUR, fiabilite, quantite, idEntreprise);

        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return prodcons;
    }
    
    @Override
    public List<ProducteurConsommateur> getProducteurs() {
        
        Connection conn;
        Statement state = null;
        List<ProducteurConsommateur> producteurs = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            producteurs = new ArrayList<ProducteurConsommateur>();
            ResultSet result = state.executeQuery(String.format("SELECT * FROM ProducteurConsommateurs WHERE statut = %d", ProducteurConsommateur.statusType.PRODUCTEUR.ordinal()));
            while (result.next()) {
                Long id = result.getLong("ID");
                double fiabilite = result.getDouble("STATUT");
                int quantite = result.getInt("FIABILITE");
                Long idEntreprise = result.getLong("FK_ENTREPRISE_3");

                producteurs.add(new ProducteurConsommateur(id, ProducteurConsommateur.statusType.PRODUCTEUR, fiabilite, quantite, idEntreprise));
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return producteurs;
    }

    @Override
    public List<ProducteurConsommateur> getConsommateurs() {
        
        Connection conn;
        Statement state = null;
        List<ProducteurConsommateur> consommateurs = null;
        try {
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            consommateurs = new ArrayList<ProducteurConsommateur>();
            ResultSet result = state.executeQuery(String.format("SELECT * FROM ProducteurConsommateurs WHERE statut = %d", ProducteurConsommateur.statusType.CONSOMMATEUR.ordinal()));
            while (result.next()) {
                Long id = result.getLong("ID");
                double fiabilite = result.getDouble("STATUT");
                int quantite = result.getInt("FIABILITE");
                Long idEntreprise = result.getLong("FK_ENTREPRISE_3");

                consommateurs.add(new ProducteurConsommateur(id, ProducteurConsommateur.statusType.CONSOMMATEUR, fiabilite, quantite, idEntreprise));
            }
        } catch (DatabaseException ex) {
            Logger.getLogger(EntrepriseDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return consommateurs;
    }
}

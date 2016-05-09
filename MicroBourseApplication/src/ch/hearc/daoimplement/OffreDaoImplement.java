package ch.hearc.daoimplement;

import ch.hearc.databasefactory.DataBaseConnection;
import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Action;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesActionDAO;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlesombangndo
 */
public class OffreDaoImplement implements ServicesOffreDao{
    
    public OffreDaoImplement (){
        
    }

    @Override
    public void createOffre(Offre offre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Offre research(Long idOffre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOffre(Offre offre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOffre(Offre offre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Offre> getOffresByEntreprise(Entreprise e) {
        
        Statement state = null;
        Connection conn = null;
        ResultSet result = null;
        List<Offre> offres = new ArrayList<>();
        try{
            conn = DataBaseConnection.getDataBase().getConnection();
            state = conn.createStatement();
            String query = "SELECT * FROM OFFRES WHERE fk_enterprise_2 = "+ e.getIdEntreprise();
            result = state.executeQuery(query);
            while(result.next()){
                Long id = result.getLong("ID");
                int quantite = result.getInt("QUANTITE");
                double prix = result.getDouble("PRIX");
                Offre.statusType status = Offre.statusType.values()[result.getInt("STATUS")];
                Offre.operationType operation = Offre.operationType.values()[result.getInt("OPERATION")];
                Date date = result.getDate("DATE_ECHANGE");
                offres.add(new Offre(id,quantite,prix,status,operation,date,null,null,null));
            }
        }catch(SQLException ex){
            ex.getMessage();
          
        } catch (DatabaseException ex) {
            Logger.getLogger(OffreDaoImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return offres;
    }

    @Override
    public void buyImmediat(Offre o, Actionnaire a, int quantite) {
        Calendar c = Calendar.getInstance();
        Offre historiqueTransaction;
        historiqueTransaction = new Offre(null, quantite, o.getPrix(), Offre.statusType.FINI, Offre.operationType.VENTE, new Date(c.get(Calendar.LONG)), o.getActionnaireOffre(), a, o.getEntreprise());
        createOffre(historiqueTransaction);
        
        if(o.getQuantite() <= quantite){
            deleteOffre(o);
        } else {
            o.setQuantite(o.getQuantite() - quantite);
            updateOffre(o);
        }
        
        //TODO
        //Check unexisting action
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        ServicesActionDAO saco = new ActionDaoImplement();
        
        Action acVendeur = saco.getActionOfActionnaireByEnterprise(historiqueTransaction.getActionnaireOffre(), historiqueTransaction.getEntreprise());
        acVendeur.setQuantite(acVendeur.getQuantite()-historiqueTransaction.getQuantite());
        Action acAcheteur = saco.getActionOfActionnaireByEnterprise(historiqueTransaction.getActionnaireOpIm(), historiqueTransaction.getEntreprise());
        acAcheteur.setQuantite(acAcheteur.getQuantite()+historiqueTransaction.getQuantite());
        
        saco.updateAction(acVendeur);
        saco.updateAction(acAcheteur);
    }

    @Override
    public void sellImmediat(Offre o, Actionnaire a, int quantite) {
        Calendar c = Calendar.getInstance();
        Offre historiqueTransaction;
        historiqueTransaction = new Offre(null, quantite, o.getPrix(), Offre.statusType.FINI, Offre.operationType.ACHAT, new Date(c.get(Calendar.LONG)), o.getActionnaireOffre(), a, o.getEntreprise());
        createOffre(historiqueTransaction);
        
        if(o.getQuantite() <= quantite){
            deleteOffre(o);
        } else {
            o.setQuantite(o.getQuantite() - quantite);
            updateOffre(o);
        }
        
        //TODO
        //Check unexisting action
        ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
        ServicesActionDAO saco = new ActionDaoImplement();
        
        Action acVendeur = saco.getActionOfActionnaireByEnterprise(historiqueTransaction.getActionnaireOpIm(), historiqueTransaction.getEntreprise());
        acVendeur.setQuantite(acVendeur.getQuantite()-historiqueTransaction.getQuantite());
        Action acAcheteur = saco.getActionOfActionnaireByEnterprise(historiqueTransaction.getActionnaireOffre(), historiqueTransaction.getEntreprise());
        acAcheteur.setQuantite(acAcheteur.getQuantite()+historiqueTransaction.getQuantite());
        
        saco.updateAction(acVendeur);
        saco.updateAction(acAcheteur);
    }

    @Override
    public void buyOffer(Actionnaire a, Entreprise e, int nbAction, int prix) {
        Calendar c = Calendar.getInstance();
        Offre newOffer = new Offre(null, nbAction, prix, Offre.statusType.EN_COURS, Offre.operationType.ACHAT, new Date(c.get(Calendar.LONG)), a, null, e);
        createOffre(newOffer);
    }

    @Override
    public void sellOffer(Actionnaire a, Entreprise e, int nbAction, int prix) {
        Calendar c = Calendar.getInstance();
        Offre newOffer = new Offre(null, nbAction, prix, Offre.statusType.EN_COURS, Offre.operationType.VENTE, new Date(c.get(Calendar.LONG)), a, null, e);
        createOffre(newOffer);
    }

}

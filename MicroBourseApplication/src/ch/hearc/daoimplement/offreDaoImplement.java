package ch.hearc.daoimplement;

import ch.hearc.metiers.Action;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesActionDAO;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

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
    public List<Offre> getOffresByEntreprise() {
        //TODO
        return null;
    }

    @Override
    public void buyImmediat(Offre o, Actionnaire a, int quantite) {
        Calendar c = Calendar.getInstance();
        Offre historiqueTransaction;
        historiqueTransaction = new Offre(null, quantite, o.getPrix(), "Fini", 0, new Date(c.get(Calendar.LONG)), o.getActionnaireOffre(), a, o.getEntreprise());
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
        historiqueTransaction = new Offre(null, quantite, o.getPrix(), "Fini", 1, new Date(c.get(Calendar.LONG)), o.getActionnaireOffre(), a, o.getEntreprise());
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
        Offre newOffer = new Offre(null, nbAction, prix, "en cours", 0, new Date(c.get(Calendar.LONG)), a, null, e);
        createOffre(newOffer);
    }

    @Override
    public void sellOffer(Actionnaire a, Entreprise e, int nbAction, int prix) {
        Calendar c = Calendar.getInstance();
        Offre newOffer = new Offre(null, nbAction, prix, "en cours", 1, new Date(c.get(Calendar.LONG)), a, null, e);
        createOffre(newOffer);
    }

}

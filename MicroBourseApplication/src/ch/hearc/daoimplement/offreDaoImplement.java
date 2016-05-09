package ch.hearc.daoimplement;

import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
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
        //mise à jour action
    }

    @Override
    public void sellImmediat(Offre o, Actionnaire a, int quantite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buyOffer(Actionnaire a, Entreprise e, int nbAction, int prix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sellOffer(Actionnaire a, Entreprise e, int nbAction, int prix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

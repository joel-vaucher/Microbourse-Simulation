/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.programme;

import ch.hearc.daoimplement.ActionDaoImplement;
import ch.hearc.daoimplement.ActionnaireDaoImplement;
import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesActionDAO;
import ch.hearc.servicesdao.ServicesActionnaireDAO;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.util.List;

/**
 *
 * @author joel
 */
public class TestOperationActionnaire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //achat immédiat Actionnaire
        {
            ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
            ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
            ServicesOffreDao soo = new OffreDaoImplement();
            int nbAction = 10;
            Actionnaire a = sao.getActionnaireByID();
            Entreprise e = seo.getEntrepriseByID(Integer.toUnsignedLong(1));
            List<Offre> offres = soo.getOffresByEntreprise(e);
            
            int nbActionAchetee = 0;
            int iOffre = 0;
            while(nbActionAchetee < nbAction) {
                int q = offres.get(iOffre).getQuantite();
                if(nbActionAchetee + q > nbAction){
                    soo.buyImmediat(offres.get(iOffre), a, q - (nbAction-nbActionAchetee));
                    nbActionAchetee = nbAction;
                } else {
                    soo.buyImmediat(offres.get(iOffre), a, q);
                    nbActionAchetee += q;
                }
            }
        }
        
        //vente immédiat Actionnaire
        {
            ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
            ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
            ServicesOffreDao soo = new OffreDaoImplement();
            int nbAction = 10;
            Actionnaire a = sao.getActionnaireByID();
            Entreprise e = seo.getEntrepriseByID(Integer.toUnsignedLong(1));
            List<Offre> offres = soo.getOffresByEntreprise(e);
            
            int nbActionVendu = 0;
            int iOffre = 0;
            while(nbActionVendu < nbAction) {
                int q = offres.get(iOffre).getQuantite();
                if(nbActionVendu + q > nbAction){
                    soo.sellImmediat(offres.get(iOffre), a, q - (nbAction-nbActionVendu));
                    nbActionVendu = nbAction;
                } else {
                    soo.sellImmediat(offres.get(iOffre), a, q);
                    nbActionVendu += q;
                }
            }
        }
        
        //offre d'achat Actionnaire
        {
            ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
            ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
            ServicesOffreDao soo = new OffreDaoImplement();
            int nbAction = 10;
            int prix = 15;
            Actionnaire a = sao.getActionnaireByID();
            Entreprise e = seo.getEntrepriseByID(Integer.toUnsignedLong(1));
            
            soo.buyOffer(a, e, nbAction, prix);
        }
        
        //offre de vente Actionnaire
        {
            ServicesActionnaireDAO sao = new ActionnaireDaoImplement();
            ServicesEntrepriseDao seo = new EntrepriseDaoImplement();
            ServicesOffreDao soo = new OffreDaoImplement();
            int nbAction = 10;
            int prix = 15;
            Actionnaire a = sao.getActionnaireByID();
            Entreprise e = seo.getEntrepriseByID(Integer.toUnsignedLong(1));
            
            soo.sellOffer(a, e, nbAction, prix);
        }
    }
    
}

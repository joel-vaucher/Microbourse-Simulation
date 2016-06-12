/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.tests;

import ch.hearc.daoimplement.ActionnaireDaoImplement;
import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.daoimplement.HistoriqueActionnaireDaoImplement;
import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueActionnaire;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesActionnaireDAO;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesHistoriqueActionnaireDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joel
 */
public class TestGetHistoriqueActionnaire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //get current
        ServicesHistoriqueActionnaireDao sha = new HistoriqueActionnaireDaoImplement();
        ServicesOffreDao soo = new OffreDaoImplement();
        ServicesActionnaireDAO manager = new ActionnaireDaoImplement();
        List<HistoriqueActionnaire> listHistorique;
        
        List<Actionnaire> actionnaires = manager.getActionnaires();
        for(Actionnaire a : actionnaires) {
            listHistorique = sha.getHistoriqueActionnaire(a.getIdActionnaire());
            System.out.println("Entreprise "+a.getIdActionnaire()+":");
            for(HistoriqueActionnaire ha : listHistorique){
                System.out.println(" - c: "+ha.getCapital()+", "+ha.getDate());
            }
        }
        System.out.println("HISTORY");
        List<Offre> HistoOffreVente;
        List<Offre> HistoOffreAchat;
        //get history
        for(Actionnaire a : actionnaires) {
            HistoOffreVente = soo.getHistoSellOffersByActionnaire(a.getIdActionnaire());
            HistoOffreAchat = soo.getHistoPurchaseOffersByActionnaire(a.getIdActionnaire());
            System.out.println("Entreprise "+a.getIdActionnaire()+" "+a.getNom()+":");
            for(Offre o : HistoOffreAchat){
                System.out.println(" - a: "+o.getIdEntreprise()+", "+o.getPrix()+", "+o.getDate()+", "+o.getIdActionnaireOffre()+", "+o.getIdActionnaireOpIm());
            }
            for(Offre o : HistoOffreVente){
                System.out.println(" - v: "+o.getIdEntreprise()+", "+o.getPrix()+", "+o.getDate()+", "+o.getIdActionnaireOffre()+", "+o.getIdActionnaireOpIm());
            }
        }
    }
    
}

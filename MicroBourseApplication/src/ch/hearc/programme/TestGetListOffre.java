/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.programme;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import ch.hearc.servicesdao.ServicesOffreDao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joel
 */
public class TestGetListOffre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //get current
        ServicesOffreDao soo = new OffreDaoImplement();
        ServicesEntrepriseDao manager = new EntrepriseDaoImplement();
        List<Offre> offresVente;
        List<Offre> offresAchat;
        
        List<Entreprise> entreprises = manager.getEntreprises();
        for(Entreprise e : entreprises) {
            offresVente = soo.getCurrentSellOffersByEntreprise(e.getIdEntreprise());
            offresAchat = soo.getCurrentPurchaseOffersByEntreprise(e.getIdEntreprise());
            System.out.println("Entreprise "+e.getIdEntreprise()+":");
            for(Offre o : offresVente){
                System.out.println(" - v: "+o.getIdEntreprise()+", "+o.getPrix()+", "+o.getDate()+", "+o.getIdActionnaireOffre());
            }
            for(Offre o : offresAchat){
                System.out.println(" - a: "+o.getIdEntreprise()+", "+o.getPrix()+", "+o.getDate()+", "+o.getIdActionnaireOffre());
            }
        }
        System.out.println("HISTORY");
        //get history
        for(Entreprise e : entreprises) {
            offresAchat = soo.getBestOffersByDay(e.getIdEntreprise(), Offre.operationType.ACHAT);
            offresVente = soo.getBestOffersByDay(e.getIdEntreprise(), Offre.operationType.VENTE);
            System.out.println("Entreprise "+e.getIdEntreprise()+":");
            for(Offre o : offresAchat){
                System.out.println(" - a: "+o.getIdEntreprise()+", "+o.getPrix()+", "+o.getDate()+", "+o.getIdActionnaireOffre());
            }
            for(Offre o : offresVente){
                System.out.println(" - v: "+o.getIdEntreprise()+", "+o.getPrix()+", "+o.getDate()+", "+o.getIdActionnaireOffre());
            }
        }
    }
    
}

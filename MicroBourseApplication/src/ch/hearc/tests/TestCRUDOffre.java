/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.tests;

import ch.hearc.daoimplement.OffreDaoImplement;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesOffreDao;

/**
 *
 * @author joel
 */
public class TestCRUDOffre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Offre o = new Offre(null, 50, 150, Offre.statusType.EN_COURS, Offre.operationType.VENTE, null, 2L, 3L, 2L);
        
        ServicesOffreDao soo = new OffreDaoImplement();
        soo.createOffre(o);
        o.setQuantite(30);
        o.setPrix(120);
        o.setIdOffre(4L);
        soo.updateOffre(o);
    }
    
}

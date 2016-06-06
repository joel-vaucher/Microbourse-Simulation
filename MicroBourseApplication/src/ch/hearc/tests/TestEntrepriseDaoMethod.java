/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.tests;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.metiers.Entreprise;
import java.util.List;

/**
 *
 * @author joel
 */
public class TestEntrepriseDaoMethod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntrepriseDaoImplement manager = new EntrepriseDaoImplement();
        
        List<Entreprise> entreprises = manager.getEntreprises();
        for(Entreprise e : entreprises) {
            System.out.println(e.getIdEntreprise() + " - " + manager.StockActuel(e.getIdEntreprise()));
        }
    }
    
}

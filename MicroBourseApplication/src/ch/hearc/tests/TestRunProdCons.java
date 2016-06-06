/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.tests;

import ch.hearc.daoimplement.ProducteurConsommateurDaoImplement;
import ch.hearc.metiers.ProducteurConsommateur;
import ch.hearc.servicesdao.ServicesProducteurConsommateurDao;
import ch.hearc.threads.RunnableConsommateur;
import ch.hearc.threads.RunnableProducteur;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author joel
 */
public class TestRunProdCons {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServicesProducteurConsommateurDao spco = new ProducteurConsommateurDaoImplement();
        List<ProducteurConsommateur> producteurs = spco.getProducteurs();
        List<ProducteurConsommateur> consommateurs = spco.getConsommateurs();
        
        
        List<Thread> threads = new LinkedList<Thread>();
        for(ProducteurConsommateur p : producteurs){
            threads.add(new Thread(new RunnableProducteur(p.getIdProducteurConsommateur())));
        }
        for(ProducteurConsommateur c : consommateurs){
            threads.add(new Thread(new RunnableConsommateur(c.getIdProducteurConsommateur())));
        }
        
        for(Thread t : threads){
            t.start();
        }
        /*
        Thread tp = new Thread(new RunnableProducteur(1L));
        tp.start();
        Thread tc = new Thread(new RunnableConsommateur(1L));
        tc.start();*/
    }
    
}

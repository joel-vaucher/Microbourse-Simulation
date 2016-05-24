/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.threads;

import ch.hearc.daoimplement.ProducteurConsommateurDaoImplement;
import ch.hearc.servicesdao.ServicesProducteurConsommateurDao;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joel
 */
public class RunnableProducteur implements Runnable{

    public RunnableProducteur(Long id){
        this.id = id;
    }

    @Override
    public void run() {
        ServicesProducteurConsommateurDao spco = new ProducteurConsommateurDaoImplement();
        while(true){
            spco.vendreRessource(id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunnableProducteur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Long id = 1L;
}

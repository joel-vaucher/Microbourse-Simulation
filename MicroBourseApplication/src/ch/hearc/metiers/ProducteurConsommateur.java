/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.metiers;

/**
 *
 * @author joel
 */
public class ProducteurConsommateur {
    private Long idProducteurConsommateur;
    private statusType status;
    private double fiabilite;
    private int quantiteRessource;
    private Long idEntreprise;
            
    public static enum statusType {
        PRODUCTEUR,
        CONSOMMATEUR
    };
    
    public ProducteurConsommateur(Long idProducteurConsommateur, statusType status, double fiabilite, int quantiteRessource, Long idEntreprise){
        this.idProducteurConsommateur = idProducteurConsommateur;
        this.status = status;
        this.fiabilite = fiabilite;
        this.quantiteRessource = quantiteRessource;
        this.idEntreprise = idEntreprise;
    }  

    public Long getIdProducteurConsommateur() {
        return idProducteurConsommateur;
    }

    public void setIdProducteurConsommateur(Long idProducteurConsommateur) {
        this.idProducteurConsommateur = idProducteurConsommateur;
    }

    public statusType getStatus() {
        return status;
    }

    public void setStatus(statusType status) {
        this.status = status;
    }

    public double getFiabilite() {
        return fiabilite;
    }

    public void setFiabilite(double fiabilite) {
        this.fiabilite = fiabilite;
    }

    public int getQuantiteRessource() {
        return quantiteRessource;
    }

    public void setQuantiteRessource(int quantiteRessource) {
        this.quantiteRessource = quantiteRessource;
    }

    public Long getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Long idEntreprise) {
        this.idEntreprise = idEntreprise;
    }
    
}

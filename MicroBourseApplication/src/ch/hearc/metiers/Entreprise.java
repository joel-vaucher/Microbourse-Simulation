package ch.hearc.metiers;

import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public class Entreprise {
    private Long idEntreprise;
    private String nom;
    private int quantiteRessource;
    private int quantiteRessourceVenteTotal;
    private double capital;
    private double capitalVenteTotal;
    
    public Entreprise(Long idEntreprise, String nom, int quantiteRessource, int quantiteRessourceVenteTotal, double capital, double capitalVenteTotal){
        this.idEntreprise = idEntreprise;
        this.nom = nom;
        this.quantiteRessource = quantiteRessource;
        this.quantiteRessourceVenteTotal = quantiteRessourceVenteTotal;
        this.capital = capital;
        this.capitalVenteTotal = capitalVenteTotal;
    }
    
    public Long getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Long idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantiteRessource() {
        return quantiteRessource;
    }

    public void setQuantiteRessource(int quantiteRessource) {
        this.quantiteRessource = quantiteRessource;
    }

    public int getQuantiteRessourceVenteTotal() {
        return quantiteRessourceVenteTotal;
    }

    public void setQuantiteRessourceVenteTotal(int quantiteRessourceVenteTotal) {
        this.quantiteRessourceVenteTotal = quantiteRessourceVenteTotal;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getCapitalVenteTotal() {
        return capitalVenteTotal;
    }

    public void setCapitalVenteTotal(double capitalVenteTotal) {
        this.capitalVenteTotal = capitalVenteTotal;
    }

    
}

package ch.hearc.metiers;

/**
 *
 * @author charlesombangndo
 */
public class Producteur {
    private Long idProducteur;
    private int fiabilite;
    private int quantiteRessource;
    private Monnaie devise;

    public Producteur(Long idProducteur) {
        this.idProducteur = idProducteur;
    }

    public Producteur(int fiabilite, int quantiteRessource, Monnaie devise) {
        this.fiabilite = fiabilite;
        this.quantiteRessource = quantiteRessource;
        this.devise = devise;
    }

    public Producteur(Long idProducteur, int fiabilite, int quantiteRessource, Monnaie devise) {
        this.idProducteur = idProducteur;
        this.fiabilite = fiabilite;
        this.quantiteRessource = quantiteRessource;
        this.devise = devise;
    }

    public Producteur() {
        this.idProducteur = null;
        this.fiabilite = 0;
        this.quantiteRessource = 0;
        this.devise = null;
    }
    
    public Long getIdProducteur() {
        return idProducteur;
    }

    public int getFiabilite() {
        return fiabilite;
    }

    public int getQuantiteRessource() {
        return quantiteRessource;
    }

    public Monnaie getDevise() {
        return devise;
    }

    public void setIdProducteur(Long idProducteur) {
        this.idProducteur = idProducteur;
    }

    public void setFiabilite(int fiabilite) {
        this.fiabilite = fiabilite;
    }

    public void setQuantiteRessource(int quantiteRessource) {
        this.quantiteRessource = quantiteRessource;
    }

    public void setDevise(Monnaie devise) {
        this.devise = devise;
    }    
}

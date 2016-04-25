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
    private List<Capital> listeCapitaux;
    private List<Producteur> listeProducteur;
    private List<Consommateur> listeConsommateur;

    public Entreprise(Long idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Entreprise(Long idEntreprise, String nom, int quantiteRessource) {
        this.idEntreprise = idEntreprise;
        this.nom = nom;
        this.quantiteRessource = quantiteRessource;
        this.listeCapitaux = null;
        this.listeProducteur = null;
        this.listeConsommateur = null;
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

    public List<Capital> getListeCapitaux() {
        return listeCapitaux;
    }

    public void setListeCapitaux(List<Capital> listeCapitaux) {
        this.listeCapitaux = listeCapitaux;
    }

    public List<Producteur> getListeProducteur() {
        return listeProducteur;
    }

    public void setListeProducteur(List<Producteur> listeProducteur) {
        this.listeProducteur = listeProducteur;
    }

    public List<Consommateur> getListeConsommateur() {
        return listeConsommateur;
    }

    public void setListeConsommateur(List<Consommateur> listeConsommateur) {
        this.listeConsommateur = listeConsommateur;
    }
}

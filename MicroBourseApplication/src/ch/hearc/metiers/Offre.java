package ch.hearc.metiers;

import java.sql.Date;

/**
 *
 * @author charlesombangndo
 */
public class Offre {
    private Long idOffre;
    private int quantite;
    private int prix;
    private String statut;
    private int operation;
    private Date date;
    private Actionnaire actionnaireOffre;
    private Actionnaire actionnaireOpIm;
    private Entreprise entreprise;

    public Offre(Long idOffre) {
        this.idOffre = idOffre;
    }

    public Offre(Long idOffre, int quantite, int prix, String statut, int operation, Date date, Actionnaire actionnaireOffre, Actionnaire actionnaireOpIm, Entreprise entreprise) {
        this.idOffre = idOffre;
        this.quantite = quantite;
        this.prix = prix;
        this.statut = statut;
        this.operation = operation;
        this.date = date;
        this.actionnaireOffre = actionnaireOffre;
        this.actionnaireOpIm = actionnaireOpIm;
        this.entreprise = entreprise;
    }

    public Long getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Long idOffre) {
        this.idOffre = idOffre;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Actionnaire getActionnaireOffre() {
        return actionnaireOffre;
    }

    public void setActionnaireOffre(Actionnaire actionnaireOffre) {
        this.actionnaireOffre = actionnaireOffre;
    }

    public Actionnaire getActionnaireOpIm() {
        return actionnaireOpIm;
    }

    public void setActionnaireOpIm(Actionnaire actionnaireOpIm) {
        this.actionnaireOpIm = actionnaireOpIm;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    
    
    
}

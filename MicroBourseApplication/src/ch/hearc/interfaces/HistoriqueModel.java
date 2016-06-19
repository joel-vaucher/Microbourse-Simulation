package ch.hearc.interfaces;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import javafx.beans.property.SimpleStringProperty;

/**
 * Classe Modèle.
 * Cette classe permet de peupler les tableaux.
 * Chacun de ses attributs correspondents à une colonne du tableau.
 */
public class HistoriqueModel {
    private final SimpleStringProperty unitPrice;
    private final SimpleStringProperty nbActions;
    private final SimpleStringProperty totalPrice;
    private final SimpleStringProperty entreprise;
    
    /**
     * Contructeur permettant de spécifier la valeur de chaque attribut
     * @param unitP     : Prix unitaire
     * @param nbA       : Nombre d'action
     * @param totalP    : Prix total
     * @param e         : Nom de l'entreprise
     */
    public HistoriqueModel(String unitP, String nbA, String totalP, String e) {
        this.unitPrice = new SimpleStringProperty(unitP);
        this.nbActions = new SimpleStringProperty(nbA);
        this.totalPrice = new SimpleStringProperty(totalP);
        this.entreprise = new SimpleStringProperty(e);
    }
    
    /**
     * Constructeur permettant d'attribuer des valeurs 
     * aux attributs en se basant sur une offre
     * @param offre : Offre 
     */
    public HistoriqueModel(Offre offre) {
        ServicesEntrepriseDao soo = new EntrepriseDaoImplement();
        Entreprise e = soo.getEntrepriseByID(offre.getIdEntreprise());
        double total = offre.getPrix() * offre.getQuantite();
        
        this.unitPrice = new SimpleStringProperty(Double.toString(offre.getPrix()));
        this.nbActions = new SimpleStringProperty(Integer.toString(offre.getQuantite()));
        this.totalPrice = new SimpleStringProperty(Double.toString(total));
        this.entreprise = new SimpleStringProperty(e.getNom());
    }
    
    /**
     * Retourne le prix unitaire
     * @return Prix unitaire
     */
    public String getUnitPrice() {
        return unitPrice.get();
    }
    
    /**
     * Affecte une valeur au prix unitaire
     * @param un : Valeur à affecter
     */
    public void setUnitPrice(String un) {
        unitPrice.set(un);
    }
    
    /**
     * Retourne le nombre d'actions
     * @return Nombre d'actions
     */
    public String getNbActions() {
        return nbActions.get();
    }
    
    /**
     * Affecte une valeur au nombre d'actions
     * @param nba : Valeur à affecter
     */
    public void setNbActions(String nba) {
        nbActions.set(nba);
    }
    
    /**
     * Retourne le prix total
     * @return Prix total
     */
    public String getTotalPrice() {
        return totalPrice.get();
    }
    
    /**
     * Affecte une valeur au prix total
     * @param tp : Valeur à affecter
     */
    public void setTotalPrice(String tp) {
        totalPrice.set(tp);
    }
        
    /**
     * Retourne le nom de l'entreprise
     * @return Nom entreprise
     */
    public String getEntreprise() {
        return entreprise.get();
    }
    /**
     * Affecte une valeur au nom de l'entreprise
     * @param e : Valeur à affecter
     */
    public void setEntreprise(String e) {
        entreprise.set(e);
    }
}

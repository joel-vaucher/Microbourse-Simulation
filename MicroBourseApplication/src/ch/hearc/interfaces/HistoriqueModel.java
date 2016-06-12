package ch.hearc.interfaces;

import ch.hearc.daoimplement.EntrepriseDaoImplement;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import ch.hearc.servicesdao.ServicesEntrepriseDao;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 */
public class HistoriqueModel {
    private final SimpleStringProperty unitPrice;
    private final SimpleStringProperty nbActions;
    private final SimpleStringProperty totalPrice;
    private final SimpleStringProperty entreprise;
    
    public HistoriqueModel(String unitP, String nbA, String totalP, String e) {
        this.unitPrice = new SimpleStringProperty(unitP);
        this.nbActions = new SimpleStringProperty(nbA);
        this.totalPrice = new SimpleStringProperty(totalP);
        this.entreprise = new SimpleStringProperty(e);
    }
    
    public HistoriqueModel(Offre offre) {
        ServicesEntrepriseDao soo = new EntrepriseDaoImplement();
        Entreprise e = soo.getEntrepriseByID(offre.getIdEntreprise());
        double total = offre.getPrix() * offre.getQuantite();
        
        this.unitPrice = new SimpleStringProperty(Double.toString(offre.getPrix()));
        this.nbActions = new SimpleStringProperty(Integer.toString(offre.getQuantite()));
        this.totalPrice = new SimpleStringProperty(Double.toString(total));
        this.entreprise = new SimpleStringProperty(e.getNom());
    }
    
    public String getUnitPrice() {
        return unitPrice.get();
    }
    
    public void setUnitPrice(String un) {
        unitPrice.set(un);
    }
    
    public String getNbActions() {
        return nbActions.get();
    }
    
    public void setNbActions(String nba) {
        nbActions.set(nba);
    }
    
    public String getTotalPrice() {
        return totalPrice.get();
    }
    
    public void setTotalPrice(String tp) {
        totalPrice.set(tp);
    }
    
    public String getEntreprise() {
        return entreprise.get();
    }
    
    public void setEntreprise(String e) {
        entreprise.set(e);
    }
}

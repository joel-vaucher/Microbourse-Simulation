package ch.hearc.metiers;

import java.sql.Date;

/**
 *
 * @author charlesombangndo
 */
public class HistoriqueEntreprise {
    private Long idHisto;
    private int quantiteRessource;
    private int quantiteRessourceVenteTotal;
    private double capital;
    private double capitalVenteTotal;
    private Date date;
    private Long idEntreprise;

    public HistoriqueEntreprise(Long idHisto, int quantiteRessource, int quantiteRessourceVenteTotal, double capital, double capitalVenteTotal, Date date, Long idEntreprise) {
        this.idHisto = idHisto;
        this.quantiteRessource = quantiteRessource;
        this.quantiteRessourceVenteTotal = quantiteRessourceVenteTotal;
        this.capital = capital;
        this.capitalVenteTotal = capitalVenteTotal;
        this.date = date;
        this.idEntreprise = idEntreprise;
    }

    public Long getIdHisto() {
        return idHisto;
    }

    public void setIdHisto(Long idHisto) {
        this.idHisto = idHisto;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Long idEntreprise) {
        this.idEntreprise = idEntreprise;
    }
    
    
}

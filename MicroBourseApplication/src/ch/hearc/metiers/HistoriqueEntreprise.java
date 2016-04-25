package ch.hearc.metiers;

import java.sql.Date;

/**
 *
 * @author charlesombangndo
 */
public class HistoriqueEntreprise {
    private Long idHisto;
    private int quantiteRessource;
    private Date date;
    private Entreprise entreprise;

    public HistoriqueEntreprise(Long idHisto, Date date, int quantiteRessource, Entreprise entreprise) {
        this.idHisto = idHisto;
        this.date = date;
        this.quantiteRessource = quantiteRessource;
        this.entreprise = entreprise;
    }

    public HistoriqueEntreprise(Long idHisto) {
        this.idHisto = idHisto;
        this.date = null;
        this.quantiteRessource = 0;
        this.entreprise = null;
    }

    public Long getIdHisto() {
        return idHisto;
    }

    public void setIdHisto(Long idHisto) {
        this.idHisto = idHisto;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getQuantiteRessource() {
        return quantiteRessource;
    }

    public void setQuantiteRessource(int quantiteRessource) {
        this.quantiteRessource = quantiteRessource;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    
    
}

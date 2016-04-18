package ch.hearc.metiers;
/**
 *
 * @author charlesombangndo
 */
public class HistoriqueEntreprise {
    private Long idHisto;
    private String date;
    private Entreprise entreprise;

    public HistoriqueEntreprise(Long idHisto, String date, Entreprise entreprise) {
        this.idHisto = idHisto;
        this.date = date;
        this.entreprise = entreprise;
    }

    public HistoriqueEntreprise(Long idHisto) {
        this.idHisto = idHisto;
        this.date = null;
        this.entreprise = null;
    }

    public Long getIdHisto() {
        return idHisto;
    }

    public void setIdHisto(Long idHisto) {
        this.idHisto = idHisto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    
    
}

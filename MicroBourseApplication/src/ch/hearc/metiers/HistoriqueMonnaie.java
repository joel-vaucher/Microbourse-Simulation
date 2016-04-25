package ch.hearc.metiers;
/**
 *
 * @author charlesombangndo
 */
public class HistoriqueMonnaie {
    private Long idHisto;
    private String date;
    private int coefProportion;
    private Monnaie devise;

    public HistoriqueMonnaie(Long idHisto, String date, int coefProportion, Monnaie devise) {
        this.idHisto = idHisto;
        this.date = date;
        this.coefProportion = coefProportion;
        this.devise = devise;
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

    public int getCoefProportion() {
        return coefProportion;
    }

    public void setCoefProportion(int coefProportion) {
        this.coefProportion = coefProportion;
    }

    public Monnaie getDevise() {
        return devise;
    }

    public void setDevise(Monnaie devise) {
        this.devise = devise;
    }   
}

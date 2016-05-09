package ch.hearc.metiers;

import java.sql.Date;

/**
 *
 * @author charlesombangndo
 */
public class HistoriqueActionnaire {
    private Long idHisto;
    private double capital;
    private Date date;
    private Long idActionnaire;

    public HistoriqueActionnaire(Long idHisto, double capital, Date date, Long idActionnaire) {
        this.idHisto = idHisto;
        this.capital = capital;
        this.date = date;
        this.idActionnaire = idActionnaire;
    }

    public Long getIdHisto() {
        return idHisto;
    }

    public void setIdHisto(Long idHisto) {
        this.idHisto = idHisto;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    
}

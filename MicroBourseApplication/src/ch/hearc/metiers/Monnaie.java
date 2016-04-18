package ch.hearc.metiers;

/**
 *
 * @author charlesombangndo
 */
public class Monnaie {
    private Long idMonnaie;
    private String nameMonnaie;

    public Monnaie(Long idMonnaie, String nameMonnaie) {
        this.idMonnaie = idMonnaie;
        this.nameMonnaie = nameMonnaie;
    }

    public Monnaie() {
        this.idMonnaie = null;
        this.nameMonnaie = null;
    }

    public Long getIdMonnaie() {
        return idMonnaie;
    }

    public void setIdMonnaie(Long idMonnaie) {
        this.idMonnaie = idMonnaie;
    }

    public String getNameMonnaie() {
        return nameMonnaie;
    }

    public void setNameMonnaie(String nameMonnaie) {
        this.nameMonnaie = nameMonnaie;
    }
    
    
    
}

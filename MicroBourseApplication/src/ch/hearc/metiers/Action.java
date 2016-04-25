package ch.hearc.metiers;

/**
 *
 * @author charlesombangndo
 */
public class Action {
    private Long idACtion;
    private int quantite;
    private Entreprise entreprise;

    public Action(Long idACtion, int quantite, Entreprise entreprise) {
        this.idACtion = idACtion;
        this.quantite = quantite;
        this.entreprise = entreprise;
    }

    public Action(Long idACtion) {
        this.idACtion = idACtion;
        this.quantite = 0;
        entreprise = null;
    }

    public Long getIdACtion() {
        return idACtion;
    }

    public int getQuantite() {
        return quantite;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setIdACtion(Long idACtion) {
        this.idACtion = idACtion;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    
    
}

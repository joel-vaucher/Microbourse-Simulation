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
    
    
}

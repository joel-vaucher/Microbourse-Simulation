package ch.hearc.metiers;

/**
 *
 * @author charlesombangndo
 */
public class Capital {
    private Long idCapitaux;
    private int quantite;
    private Monnaie devise;

    public Capital(Long idCapitaux, int quantite, Monnaie devise) {
        this.idCapitaux = idCapitaux;
        this.quantite = quantite;
        this.devise = devise;
    }

    public Capital(Long idCapitaux) {
        this.idCapitaux = idCapitaux;
    }

    public Capital(int quantite, Monnaie devise) {
        this.idCapitaux = null;
        this.quantite = quantite;
        this.devise = devise;
    }
}
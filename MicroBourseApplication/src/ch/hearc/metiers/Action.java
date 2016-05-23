package ch.hearc.metiers;

/**
 *
 * @author charlesombangndo
 */
public class Action {
    private Long idAction;
    private int quantite;
    private Long idEntreprise;
    private Long idActionnaire;

    public Action(Long idAction, int quantite, Long idEntreprise, Long idActionnaire) {
        this.idAction = idAction;
        this.quantite = quantite;
        this.idEntreprise = idEntreprise;
        this.idActionnaire = idActionnaire;
    }

    public Long getIdAction() {
        return idAction;
    }

    public void setIdAction(Long idACtion) {
        this.idAction = idACtion;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Long getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Long idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }
    
    
}

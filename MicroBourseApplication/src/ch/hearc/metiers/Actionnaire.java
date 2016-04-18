package ch.hearc.metiers;

import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public class Actionnaire {
    private Long idActionnaire;
    private String nom;
    private List<Capital> listeCapitaux;
    private List<Action> listeActions;

    public Actionnaire(Long idActionnaire, String nom) {
        this.idActionnaire = idActionnaire;
        this.nom = nom;
        this.listeActions = null;
        this.listeCapitaux = null;
    }

    public Actionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
        this.nom = null;
        this.listeCapitaux = null;
        this.listeActions = null;
    }

    public Long getIdActionnaire() {
        return idActionnaire;
    }

    public void setIdActionnaire(Long idActionnaire) {
        this.idActionnaire = idActionnaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Capital> getListeCapitaux() {
        return listeCapitaux;
    }

    public void setListeCapitaux(List<Capital> listeCapitaux) {
        this.listeCapitaux = listeCapitaux;
    }

    public List<Action> getListeActions() {
        return listeActions;
    }

    public void setListeActions(List<Action> listeActions) {
        this.listeActions = listeActions;
    }
}

package ch.hearc.metiers;

import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public class Actionnaire {
    private Long idActionnaire;
    private String nom;
    private double capital;

    public Actionnaire(Long idActionnaire, String nom, double capital) {
        this.idActionnaire = idActionnaire;
        this.nom = nom;
        this.capital = capital;
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

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }
}

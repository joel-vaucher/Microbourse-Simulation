package ch.hearc.servicesdao;

import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Offre;
import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesOffreDao {
    
    void createOffre(Offre offre);
    Offre research(Long idOffre);
    void updateOffre(Offre offre);
    void deleteOffre(Offre offre);   

    public List<Offre> getOffresByEntreprise(Entreprise e);

    public void buyImmediat(Offre o, Actionnaire a, int quantite);

    public void sellImmediat(Offre o, Actionnaire a, int quantite);

    public void buyOffer(Actionnaire a, Entreprise e, int nbAction, int prix);

    public void sellOffer(Actionnaire a, Entreprise e, int nbAction, int prix);
}

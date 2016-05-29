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
    
    public void createOffre(Offre offre);
    public Offre getOffreByID(Long idOffre);
    public void updateOffre(Offre offre);
    public void deleteOffre(Offre offre);   

    public List<Offre> getCurrentSellOffersByEntreprise(Long idE);
    
    public List<Offre> getCurrentPurchaseOffersByEntreprise(Long idE);

    public void buyImmediat(Offre o, Long idA, int quantite);

    public void sellImmediat(Offre o, Long idA, int quantite);

    public void buyOffer(Long idA, Long idE, int nbAction, int prix);

    public void sellOffer(Long idA, Long idE, int nbAction, int prix);
}

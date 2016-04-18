package ch.hearc.servicesdao;

import ch.hearc.metiers.Offre;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesOffreDao {
    
    void createOffre(Offre offre);
    Offre research(Long idOffre);
    void updateOffre(Offre offre);
    void deleteOffre(Offre offre);   
}

package ch.hearc.servicesdao;

import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesHistoriqueEntrepriseDao {
    
    public void createHistoriqueEntreprise(HistoriqueEntreprise h);
    
    public void createHistoriqueEntreprise(Entreprise e);
}

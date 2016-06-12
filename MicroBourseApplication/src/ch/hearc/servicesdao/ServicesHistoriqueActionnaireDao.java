package ch.hearc.servicesdao;

import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueActionnaire;
import ch.hearc.metiers.HistoriqueEntreprise;
import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesHistoriqueActionnaireDao {
    public void createHistoriqueActionnaire(Actionnaire a);
    
    public void createHistoriqueActionnaire(HistoriqueActionnaire h);
    
    public List<HistoriqueActionnaire> getHistoriqueActionnaire(Long idActionnaire);
}

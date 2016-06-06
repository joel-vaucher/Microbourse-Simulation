package ch.hearc.servicesdao;

import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueActionnaire;
import ch.hearc.metiers.HistoriqueEntreprise;
import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesHistoriqueActionnaireDao {
    public List<HistoriqueActionnaire> getHistoriqueActionnaire(Long idActionnaire);
}

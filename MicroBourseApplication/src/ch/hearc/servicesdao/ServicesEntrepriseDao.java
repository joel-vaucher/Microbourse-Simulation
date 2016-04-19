package ch.hearc.servicesdao;

import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesEntrepriseDao {
    
    double StockActuel(Entreprise entreprise);
    
    double ConsommationMensuelle(Entreprise entreprise);
    
    List<HistoriqueEntreprise> getHistoriqueOf(Entreprise entreprise);
    
    Entreprise getEntrepriseByID(Long idEntreprise);
}

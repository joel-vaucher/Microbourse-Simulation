package ch.hearc.servicesdao;

import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.HistoriqueEntreprise;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesEntrepriseDao {
    
    public void recordEntreprise(Long idE);
    
    public double StockActuel(Long idE);
    
    double ConsommationMensuelle(Long idE);
    
    HistoriqueEntreprise getNearestHistorique(Long idE, Date date);
    
    List<HistoriqueEntreprise> getHistoriqueOf(Long idE);
    
    Entreprise getEntrepriseByID(Long idE);
    
    List<Entreprise> getEntreprises();
}

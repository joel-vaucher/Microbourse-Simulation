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
    
    public double StockActuel(Entreprise entreprise);
    
    double ConsommationMensuelle(Entreprise entreprise);
    
    HistoriqueEntreprise getNearestHistorique(Entreprise entreprise, Date date);
    
    List<HistoriqueEntreprise> getHistoriqueOf(Entreprise entreprise);
    
    Entreprise getEntrepriseByID(Long idEntreprise);
    
    List<Entreprise> getEntreprises();
}

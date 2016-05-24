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
    
    public void updateEntreprise(Entreprise entreprise);
    
    public void recordEntreprise(Long idE);
    
    public double StockActuel(Long idE);
    
    public void SellRessource(Long idE, int quantiteRessource, double prixUnitaire);
    
    public void BuyRessource(Long idE, int quantiteRessource, double prixUnitaire);
    
    public double ConsommationMensuelle(Long idE);
    
    public HistoriqueEntreprise getNearestHistorique(Long idE, Date date);
    
    public List<HistoriqueEntreprise> getHistoriqueOf(Long idE);
    
    public Entreprise getEntrepriseByID(Long idE);
    
    public List<Entreprise> getEntreprises();
}

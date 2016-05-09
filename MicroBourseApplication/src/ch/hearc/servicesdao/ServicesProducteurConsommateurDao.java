package ch.hearc.servicesdao;

import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.ProducteurConsommateur;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesProducteurConsommateurDao {
    
    public void vendreRessource(Long prod); 
    public void acheterRessource(Long cons);
    public Entreprise getEntrepriseById(Long idProdCons);
}

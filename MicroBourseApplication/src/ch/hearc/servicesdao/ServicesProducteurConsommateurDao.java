package ch.hearc.servicesdao;

import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Producteur;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesProducteurConsommateurDao {
    
    public void vendreRessource(Producteur prod); 
    public void acheterRessource(Producteur cons);
    public Entreprise getEntrepriseById(long idProdCons);
}

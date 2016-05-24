package ch.hearc.servicesdao;

import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.ProducteurConsommateur;
import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesProducteurConsommateurDao {
    
    public void vendreRessource(Long prod); 
    public void acheterRessource(Long cons);
    public Entreprise getEntrepriseById(Long idProdCons);
    public ProducteurConsommateur getProducteurConsommateurById(Long idProdCons);
    public List<ProducteurConsommateur> getProducteurs();
    public List<ProducteurConsommateur> getConsommateurs();
    
    /*
    public ServicesProducteurConsommateurDao getInstanceWrite();
    public ServicesProducteurConsommateurDao getInsttanceRead();
    */
}

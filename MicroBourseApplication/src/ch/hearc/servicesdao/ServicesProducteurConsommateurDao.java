package ch.hearc.servicesdao;

import ch.hearc.metiers.Producteur;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesProducteurConsommateurDao {
    
    void vendreRessource(Producteur prod); 
    void acheterRessource(Producteur cons);
}

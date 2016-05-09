package ch.hearc.servicesdao;

import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Action;
import ch.hearc.metiers.Actionnaire;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesActionnaireDAO{
    
    //Retourner le nombre d'action 
    int getQuantiteAction(Actionnaire actionnaire, Action action) throws DatabaseException;

    public Actionnaire getActionnaireByID(Long idA);
}

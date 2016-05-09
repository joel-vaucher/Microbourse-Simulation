package ch.hearc.servicesdao;

import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Action;
import ch.hearc.metiers.Actionnaire;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesActionDAO{
    
    public Action getActionByID();
}

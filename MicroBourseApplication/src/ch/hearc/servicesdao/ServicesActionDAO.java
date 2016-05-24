package ch.hearc.servicesdao;

//import ch.hearc.exception.DatabaseException;
import ch.hearc.metiers.Action;
//import ch.hearc.metiers.Actionnaire;
//import ch.hearc.metiers.Entreprise;
//import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesActionDAO{
    
    public void createAction(Action action);
    
    public Action getActionByID(Long idAction);
    
    public Action getActionOfActionnaireByEnterprise(Long idA, Long idE);

    public void updateAction(Action acVendeur);
}

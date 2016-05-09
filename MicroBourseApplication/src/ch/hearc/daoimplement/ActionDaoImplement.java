/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.daoimplement;
import ch.hearc.metiers.Action;
import ch.hearc.metiers.Actionnaire;
import ch.hearc.metiers.Entreprise;
import ch.hearc.servicesdao.ServicesActionDAO;
import java.util.List;

/**
 *
 * @author charlesombangndo
 */
public class ActionDaoImplement implements ServicesActionDAO{

    public ActionDaoImplement(){
        
    }
    
    @Override
    public Action getActionByID() {
        //TODO
        return null;
    }

    @Override
    public void updateAction(Action acVendeur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Action getActionOfActionnaireByEnterprise(Long idA, Long idE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

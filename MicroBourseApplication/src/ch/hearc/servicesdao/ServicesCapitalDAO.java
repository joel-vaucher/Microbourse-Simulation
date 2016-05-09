package ch.hearc.servicesdao;

import java.util.List;
import ch.hearc.metiers.Capital;
import ch.hearc.metiers.Entreprise;
import ch.hearc.metiers.Monnaie;

/**
 *
 * @author charlesombangndo
 */
public interface ServicesCapitalDAO {
    
    //Capitaux de l'entreprise pour les différentes devises
    List<Capital> getCapitauxbyEntreprise(Long idEntreprise);
    //Retourne la fortune d'une entreprise
    double getFortuneByMonnaie(Entreprise entreprise, Monnaie idMonnaie); 
}

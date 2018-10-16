package fr.project.mythe.api.services;

import fr.project.mythe.api.model.InseeEntreprise;

/**
 * @Project sirene
 * @Author Henri Joel SEDJAME
 * @Date 14/10/2018
 * @Class purposes : .......
 */
public interface InseeService {

  /**
   * Methode qui permet de lancer un appel à l'api de l'insee
   * @param siren
   * @return
   */
 InseeEntreprise getEntrepriseBySirenOrSiret(String siren);
}

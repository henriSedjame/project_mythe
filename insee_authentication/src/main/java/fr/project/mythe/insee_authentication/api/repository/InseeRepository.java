package fr.project.mythe.insee_authentication.api.repository;

import fr.project.mythe.insee_authentication.api.model.InseeEntreprise;

public interface InseeRepository {

  /**
   * Methode qui permet de lancer un appel à l'api de l'insee
   * @param siren
   * @return
   */
  InseeEntreprise getEntrepriseBySirenOrSiret(String siren);
}

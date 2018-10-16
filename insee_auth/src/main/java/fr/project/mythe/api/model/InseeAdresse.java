package fr.project.mythe.api.model;

import lombok.Builder;

/**
 * @Project project_mythe
 * @Author Henri Joel SEDJAME
 * @Date 16/10/2018
 * @Class purposes : .......
 */
@Builder
public class InseeAdresse {
  private InseeVoie rue;
  private String ville;
  private String codePostal;
  private String codeInseeLocalite;
  private String cedex;
}

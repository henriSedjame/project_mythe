package fr.project.mythe.api.model;

import lombok.Builder;

/**
 * @Project project_mythe
 * @Author Henri Joel SEDJAME
 * @Date 16/10/2018
 * @Class purposes : .......
 */
@Builder
public class InseeMandataire {
  private String nom;
  private String prenom;
  private String fonction;
  private String type;
}

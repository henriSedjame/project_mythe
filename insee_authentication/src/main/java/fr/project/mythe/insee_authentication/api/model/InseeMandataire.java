package fr.project.mythe.insee_authentication.api.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InseeMandataire {
  private String nom;
  private String prenom;
  private String fonction;
  private String type;
}

package fr.project.mythe.insee_authentication.api.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InseeAdresse {
  private InseeVoie rue;
  private String ville;
  private String codePostal;
  private String codeInseeLocalite;
  private String cedex;
}

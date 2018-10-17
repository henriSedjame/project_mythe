package fr.project.mythe.insee_authentication.api.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InseeSiege {
  private String naf;
  private String libelleNaf;
  private InseeAdresse adresse;
  private InseeTrancheEffectif trancheEffectif;
  private String enseigne;
  private String regionImplantation;
  private String communeImplantation;
}

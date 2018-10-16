package fr.project.mythe.api.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @Project project_mythe
 * @Author Henri Joel SEDJAME
 * @Date 16/10/2018
 * @Class purposes : .......
 */
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

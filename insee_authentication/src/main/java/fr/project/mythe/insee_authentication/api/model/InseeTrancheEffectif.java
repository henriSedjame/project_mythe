package fr.project.mythe.insee_authentication.api.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InseeTrancheEffectif {
  private Integer effectifMinimum;
  private Integer effectifMaximum;
  private String intitule;
}

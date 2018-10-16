package fr.project.mythe.api.model;

import lombok.Builder;

/**
 * @Project project_mythe
 * @Author Henri Joel SEDJAME
 * @Date 16/10/2018
 * @Class purposes : .......
 */
@Builder
public class InseeTrancheEffectif {
  private Integer effectifMinimum;
  private Integer effectifMaximum;
  private String intitule;
}

package fr.project.mythe.api.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Project project_mythe
 * @Author Henri Joel SEDJAME
 * @Date 16/10/2018
 * @Class purposes : .......
 */
@Builder
@Getter
public class InseeEntreprise {


  private String siren;
  private String siret;
  private String categorie;
  private String nomCommercial;
  private String formeJuridique;
  private LocalDate dateCreation;
  private LocalDate dateRadiation;
  private InseeSiege siegeSocial;
  private Collection<InseeMandataire> mandataires = new ArrayList<>();

  /**
   * Methode permettant de savoir si l'entreprise a été radiée
   * @return
   */
  public boolean isOff(){
    return dateRadiation != null && dateRadiation.isBefore(LocalDate.now());
  }
}

package fr.project.mythe.utils;

import fr.project.mythe.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project project_mythe
 * @Author Henri Joel SEDJAME
 * @Date 16/10/2018
 * @Class purposes : .......
 */
@Slf4j
@Component
public class InseeResponseConverter {

  public InseeEntreprise convertResponseToEntrepriseObject(String response) {
    JSONObject json = new JSONObject(response);

    final JSONObject entreprise = (JSONObject) json.get("entreprise");
    final JSONObject siege = (JSONObject) json.get("etablissement_siege");
    final boolean error = (boolean) json.get("gateway_error");

    final String siren = this.stringify(entreprise.get("siren"));
    final String categorie = this.stringify(entreprise.get("categorie_entreprise"));
    final String nomCommercial = this.stringify(entreprise.get("nom_commercial"));
    final String formeJuridique = this.stringify(entreprise.get("forme_juridique"));
    final LocalDate dateCreation = this.dateOf(this.numberOf(this.stringify(entreprise.get("date_creation"))));
    final LocalDate dateRadiation = this.dateOf(this.numberOf(this.stringify(entreprise.get("date_radiation"))));


    final String siret = this.stringify(siege.get("siret"));
    final String naf = this.stringify(siege.get("naf"));
    final String libelleNaf = this.stringify(siege.get("libelle_naf"));
    final String enseigne = this.stringify(siege.get("enseigne"));
    final JSONObject region_implantation = (JSONObject) siege.get("region_implantation");
    final JSONObject commune_implantation = (JSONObject) siege.get("commune_implantation");
    final String regionImplantation = this.stringify(region_implantation.get("value"));
    final String communeImplantation = this.stringify(commune_implantation.get("value"));

    final JSONObject adresse = (JSONObject) siege.get("adresse");
    InseeAdresse _adresse = null;
    if (adresse != null) {
      final String numeroVoie = this.stringify(adresse.get("numero_voie"));
      final String typeVoie = this.stringify(adresse.get("type_voie"));
      final String nomVoie = this.stringify(adresse.get("nom_voie"));
      final String ville = this.stringify(adresse.get("localite"));
      final String codePostal = this.stringify(adresse.get("code_postal"));
      final String codeInseeLocalite = this.stringify(adresse.get("code_insee_localite"));
      final String cedex = this.stringify(adresse.get("cedex"));
      _adresse = InseeAdresse
        .builder()
        .rue(InseeVoie
          .builder()
          .nomVoie(nomVoie)
          .numeroVoie(numeroVoie)
          .typeVoie(typeVoie)
          .build())
        .ville(ville)
        .codePostal(codePostal)
        .codeInseeLocalite(codeInseeLocalite)
        .build();
    }
    final JSONObject trancheEffectifSalarieEtablissement = (JSONObject) siege.get("tranche_effectif_salarie_etablissement");
    InseeTrancheEffectif _traTrancheEffectif = null;
    if (trancheEffectifSalarieEtablissement != null) {
      final Integer effectifMinimum = this.numberOf(this.stringify(trancheEffectifSalarieEtablissement.get("de")));
      final Integer effectifMaximum = this.numberOf(this.stringify(trancheEffectifSalarieEtablissement.get("a")));
      final String intitule = this.stringify(trancheEffectifSalarieEtablissement.get("intitule"));
      _traTrancheEffectif = InseeTrancheEffectif
        .builder()
        .effectifMaximum(effectifMaximum)
        .effectifMinimum(effectifMinimum)
        .intitule(intitule)
        .build();
    }


    final JSONArray mandataires = (JSONArray) entreprise.get("mandataires_sociaux");
    List<InseeMandataire> _mandataires = new ArrayList<>();
    if (mandataires != null) {
      mandataires.toList().forEach(object -> {
        final int index = mandataires.toList().indexOf(object);
        final JSONObject mand = mandataires.getJSONObject(index);
        final String nom = this.stringify(mand.get("nom"));
        final String prenom = this.stringify(mand.get("prenom"));
        final String fonction = this.stringify(mand.get("fonction"));
        final String type = this.stringify(mand.get("type"));

        _mandataires.add(InseeMandataire
          .builder()
          .nom(nom)
          .prenom(prenom)
          .fonction(fonction)
          .type(type)
          .build());
      });
    }
    return InseeEntreprise.builder()
      .siren(siren)
      .siret(siret)
      .categorie(categorie)
      .nomCommercial(nomCommercial)
      .formeJuridique(formeJuridique)
      .dateCreation(dateCreation)
      .dateRadiation(dateRadiation)
      .siegeSocial(InseeSiege
        .builder()
        .naf(naf)
        .libelleNaf(libelleNaf)
        .enseigne(enseigne)
        .regionImplantation(regionImplantation)
        .communeImplantation(communeImplantation)
        .adresse(_adresse)
        .trancheEffectif(_traTrancheEffectif)
        .build())
      .mandataires(_mandataires)
      .build();

  }
  private String stringify(Object object){
    if (object  == null) return StringUtils.EMPTY; else return object.toString();
  }

  private Integer numberOf(String string){
    Integer result = 0;
    try {
      result = Integer.parseInt(string);
    } catch (NumberFormatException e){
      log.debug(e.getMessage());
    }
    return result;
  }

  private LocalDate dateOf(Integer number){
    if(number == 0) return null;
    return new Timestamp(number).toLocalDateTime().toLocalDate();
  }
}

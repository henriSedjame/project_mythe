package fr.project.mythe.utils;

import fr.project.mythe.api.model.InseeEntreprise;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Project project_mythe
 * @Author Henri Joel SEDJAME
 * @Date 16/10/2018
 * @Class purposes : .......
 */
class InseeResponseConverterTest {

  String reponse = "{" +
    "  \"entreprise\": {\n" +
    "    \"siren\": \"418166096\",\n" +
    "      \"capital_social\": 459356,\n" +
    "      \"numero_tva_intracommunautaire\": \"FR16418166096\",\n" +
    "      \"forme_juridique\": \"SA à directoire (s.a.i.)\",\n" +
    "      \"forme_juridique_code\": \"5699\",\n" +
    "      \"nom_commercial\": \"OCTO-TECHNOLOGY\",\n" +
    "      \"procedure_collective\": false,\n" +
    "      \"enseigne\": null,\n" +
    "      \"raison_sociale\": \"OCTO-TECHNOLOGY\",\n" +
    "      \"siret_siege_social\": \"41816609600051\",\n" +
    "      \"code_effectif_entreprise\": \"31\",\n" +
    "      \"date_creation\": 891381600,\n" +
    "      \"nom\": null,\n" +
    "      \"prenom\": null,\n" +
    "      \"date_radiation\": null,\n" +
    "      \"categorie_entreprise\": \"PME\",\n" +
    "      \"tranche_effectif_salarie_entreprise\": {\n" +
    "        \"de\": 200,\n" +
    "        \"a\": 249,\n" +
    "        \"code\": \"31\",\n" +
    "        \"date_reference\": \"2014\",\n" +
    "        \"intitule\": \"200 à 249 salariés\"\n" +
    "      },\n" +
    "      \"mandataires_sociaux\": [{\n" +
    "        \"nom\": \"HISQUIN\",\n" +
    "        \"prenom\": \"FRANCOIS\",\n" +
    "        \"fonction\": \"PRESIDENT DU DIRECTOIRE\",\n" +
    "        \"dirigeant\": true,\n" +
    "        \"date_naissance\": \"1965-01-27\",\n" +
    "        \"date_naissance_timestamp\": -155523600,\n" +
    "        \"raison_sociale\": \"\",\n" +
    "        \"identifiant\": \"\",\n" +
    "        \"type\": \"PP\"\n" +
    "      }, {\n" +
    "        \"nom\": \"BONTE\",\n" +
    "        \"prenom\": \"NICOLAS\",\n" +
    "        \"fonction\": \"PRESIDENT DU CONSEIL DE SURVEILLANCE\",\n" +
    "        \"dirigeant\": true,\n" +
    "        \"date_naissance\": \"1965-01-21\",\n" +
    "        \"date_naissance_timestamp\": -156042000,\n" +
    "        \"raison_sociale\": \"\",\n" +
    "        \"identifiant\": \"\",\n" +
    "        \"type\": \"PP\"\n" +
    "      }, {\n" +
    "        \"nom\": \"BOSQUE\",\n" +
    "          \"prenom\": \"WILLIAM\",\n" +
    "          \"fonction\": \"VICE PRESIDENT ET MEMBRE DU CONSEIL DE SURVEILLANCE\",\n" +
    "          \"dirigeant\": true,\n" +
    "          \"date_naissance\": \"1970-12-31\",\n" +
    "          \"date_naissance_timestamp\": 31446000,\n" +
    "          \"raison_sociale\": \"\",\n" +
    "          \"identifiant\": \"\",\n" +
    "          \"type\": \"PP\"\n" +
    "      }, {\n" +
    "        \"nom\": \"CINQUIN\",\n" +
    "          \"prenom\": \"LUDOVIC\",\n" +
    "          \"fonction\": \"MEMBRE DU DIRECTOIRE\",\n" +
    "          \"dirigeant\": true,\n" +
    "          \"date_naissance\": \"1972-01-25\",\n" +
    "          \"date_naissance_timestamp\": 65142000,\n" +
    "          \"raison_sociale\": \"\",\n" +
    "          \"identifiant\": \"\",\n" +
    "          \"type\": \"PP\"\n" +
    "      }, {\n" +
    "        \"nom\": \"LUCAS\",\n" +
    "          \"prenom\": \"JACQUES\",\n" +
    "          \"fonction\": \"MEMBRE DU CONSEIL DE SURVEILLANCE\",\n" +
    "          \"dirigeant\": true,\n" +
    "          \"date_naissance\": \"1964-12-02\",\n" +
    "          \"date_naissance_timestamp\": -160362000,\n" +
    "          \"raison_sociale\": \"\",\n" +
    "          \"identifiant\": \"\",\n" +
    "          \"type\": \"PP\"\n" +
    "      }, {\n" +
    "        \"nom\": \"DEGONSE\",\n" +
    "          \"prenom\": \"GERARD\",\n" +
    "          \"fonction\": \"MEMBRE DU CONSEIL DE SURVEILLANCE\",\n" +
    "          \"dirigeant\": true,\n" +
    "          \"date_naissance\": \"1947-07-03\",\n" +
    "          \"date_naissance_timestamp\": -710038800,\n" +
    "          \"raison_sociale\": \"\",\n" +
    "          \"identifiant\": \"\",\n" +
    "          \"type\": \"PP\"\n" +
    "      }, {\n" +
    "        \"nom\": \"PLANTIN\",\n" +
    "          \"prenom\": \"JEAN-FRANCOIS\",\n" +
    "          \"fonction\": \"COMMISSAIRE AUX COMPTES TITULAIRE\",\n" +
    "          \"dirigeant\": true,\n" +
    "          \"date_naissance\": \"1959-01-27\",\n" +
    "          \"date_naissance_timestamp\": -344912400,\n" +
    "          \"raison_sociale\": \"\",\n" +
    "          \"identifiant\": \"\",\n" +
    "          \"type\": \"PP\"\n" +
    "      }, {\n" +
    "        \"nom\": \"\",\n" +
    "          \"prenom\": \"\",\n" +
    "          \"fonction\": \"COMMISSAIRE AUX COMPTES SUPPLEANT\",\n" +
    "          \"dirigeant\": true,\n" +
    "          \"date_naissance\": \"\",\n" +
    "          \"date_naissance_timestamp\": 0,\n" +
    "          \"raison_sociale\": \"BCRH & ASSOCIES - SOCIETE A RESPONSABILITE LIMITEE A ASSOCIE UNIQUE\",\n" +
    "          \"identifiant\": \"490092574\",\n" +
    "          \"type\": \"PM\"\n" +
    "      }]\n" +
    "  },\n" +
    "    \"etablissement_siege\": {\n" +
    "      \"siege_social\": true,\n" +
    "      \"siret\": \"41816609600051\",\n" +
    "      \"naf\": \"6202A\",\n" +
    "      \"libelle_naf\": \"Conseil en systèmes et logiciels informatiques\",\n" +
    "      \"date_mise_a_jour\": 1449183600,\n" +
    "      \"tranche_effectif_salarie_etablissement\": {\n" +
    "        \"de\": 200,\n" +
    "        \"a\": 249,\n" +
    "        \"code\": \"31\",\n" +
    "        \"date_reference\": \"2014\",\n" +
    "        \"intitule\": \"200 à 249 salariés\"\n" +
    "      },\n" +
    "      \"date_creation_etablissement\": 1108594800,\n" +
    "      \"enseigne\": null,\n" +
    "      \"region_implantation\": {\n" +
    "        \"code\": \"11\",\n" +
    "        \"value\": \"Île-de-France\"\n" +
    "      },\n" +
    "      \"commune_implantation\": {\n" +
    "        \"code\": \"75108\",\n" +
    "        \"value\": \"PARIS 8\"\n" +
    "      },\n" +
    "      \"pays_implantation\": {\n" +
    "        \"code\": null,\n" +
    "        \"value\": null\n" +
    "      },\n" +
    "      \"diffusable_commercialement\": null,\n" +
    "      \"adresse\": {\n" +
    "        \"l1\": \"OCTO TECHNOLOGY\",\n" +
    "        \"l2\": null,\n" +
    "        \"l3\": null,\n" +
    "        \"l4\": \"50 AVENUE DES CHAMPS ELYSEES\",\n" +
    "        \"l5\": null,\n" +
    "        \"l6\": \"75008 PARIS\",\n" +
    "        \"l7\": \"FRANCE\",\n" +
    "        \"numero_voie\": \"50\",\n" +
    "        \"type_voie\": \"AV\",\n" +
    "        \"nom_voie\": \"DES CHAMPS ELYSEES\",\n" +
    "        \"complement_adresse\": null,\n" +
    "        \"code_postal\": \"75008\",\n" +
    "        \"localite\": \"PARIS 8\",\n" +
    "        \"code_insee_localite\": \"75108\",\n" +
    "        \"cedex\": null\n" +
    "      }\n" +
    "    },\n" +
    "    \"gateway_error\": false\n" +
    "}";

  InseeResponseConverter converter;

  @BeforeEach
  void setUp() {
    converter = new InseeResponseConverter();
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void convertResponseToEntrepriseObject() throws IOException {
    final InseeEntreprise entreprise = converter.convertResponseToEntrepriseObject(reponse);
    assertAll("entreprise",
      () -> assertNotNull(entreprise),
      () -> assertFalse(entreprise.isOff()),
      () -> assertEquals("41816609600051", entreprise.getSiret()),
      () -> assertEquals(8, entreprise.getMandataires().size()),
      () -> assertEquals("Conseil en systèmes et logiciels informatiques", entreprise.getSiegeSocial().getLibelleNaf())
    );
  }
}

package fr.project.mythe.insee_authentication.api.repository.impl;

import fr.project.mythe.insee_authentication.api.model.InseeEntreprise;
import fr.project.mythe.insee_authentication.api.repository.InseeRepository;
import fr.project.mythe.insee_authentication.utils.InseeConstantes;
import fr.project.mythe.insee_authentication.utils.InseeRequestCallBack;
import fr.project.mythe.insee_authentication.utils.InseeResponseExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
@Slf4j
public class InseeRepositoryImpl implements InseeRepository {

  private InseeResponseExtractor responseExtractor;
  private InseeRequestCallBack requestCallBack;

  public InseeRepositoryImpl(InseeResponseExtractor extractor, InseeRequestCallBack requestCallBack) {
    this.responseExtractor = extractor;
    this.requestCallBack = requestCallBack;
  }

  /**
   * Methode qui permet de lancer un appel à l'api de l'insee
   *
   * @param siren
   * @return
   */
  @Override
  public InseeEntreprise getEntrepriseBySirenOrSiret(String siren) {

    RestTemplate template = new RestTemplate();

    log.info("Construction de l'uri de l'appel à l'api de l'Insee");

    String uri = buildInseeUri(siren);

    log.info("URI : " + uri);

    return template.execute(uri, HttpMethod.GET, this.requestCallBack, this.responseExtractor);
  }

  /**
   * Méthode de construction de l'url d'appel à l'api Insee
   * @param siren
   * @return
   */
  private String buildInseeUri(String siren) {
    return UriComponentsBuilder
              .fromHttpUrl(InseeConstantes.BASE_URL_VALUE)
              .queryParam(InseeConstantes.SIREN, siren)
              .queryParam(InseeConstantes.TOKEN, InseeConstantes.TOKEN_VALUE)
              .queryParam(InseeConstantes.CONTEXT, InseeConstantes.CONTEXT_VALUE)
              .queryParam(InseeConstantes.RECIPIENT, InseeConstantes.RECIPIENT_VALUE)
              .queryParam(InseeConstantes.OBJECT, InseeConstantes.OBJECT_VALUE)
              .toUriString();
  }
}

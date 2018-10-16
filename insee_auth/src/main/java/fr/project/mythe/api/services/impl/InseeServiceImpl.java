package fr.project.mythe.api.services.impl;


import fr.project.mythe.api.model.InseeEntreprise;
import fr.project.mythe.api.services.InseeService;
import fr.project.mythe.utils.InseeConstantes;
import fr.project.mythe.utils.InseeRequestCallBack;
import fr.project.mythe.utils.InseeResponseExtractor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Project sirene
 * @Author Henri Joel SEDJAME
 * @Date 14/10/2018
 * @Class purposes : .......
 */
@Service
public class InseeServiceImpl implements InseeService {

  private InseeResponseExtractor responseExtractor;
  private InseeRequestCallBack requestCallBack;

  public InseeServiceImpl(InseeResponseExtractor extractor, InseeRequestCallBack requestCallBack) {
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

    String uri = UriComponentsBuilder
      .fromHttpUrl(InseeConstantes.BASE_URL_VALUE)
      .queryParam(InseeConstantes.SIREN, siren)
      .queryParam(InseeConstantes.TOKEN, InseeConstantes.TOKEN_VALUE)
      .queryParam(InseeConstantes.CONTEXT, InseeConstantes.CONTEXT_VALUE)
      .queryParam(InseeConstantes.RECIPIENT, InseeConstantes.RECIPIENT_VALUE)
      .queryParam(InseeConstantes.OBJECT, InseeConstantes.OBJECT_VALUE)
      .toUriString();

    return template.execute(uri, HttpMethod.GET, this.requestCallBack, this.responseExtractor);
  }
}

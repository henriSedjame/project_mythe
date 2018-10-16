package fr.project.mythe.utils;

import fr.project.mythe.api.model.InseeEntreprise;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;


/**
 * @Project sirene
 * @Author Henri Joel SEDJAME
 * @Date 14/10/2018
 * @Class purposes : .......
 */
@Component
public class InseeResponseExtractor implements ResponseExtractor<InseeEntreprise> {


  private InseeResponseConverter converter;

  public InseeResponseExtractor(InseeResponseConverter converter) {
    this.converter = converter;
  }

  @Override
  public InseeEntreprise extractData(ClientHttpResponse response) throws IOException {
    final String body = response.getBody().toString();
    return this.converter.convertResponseToEntrepriseObject(body);
  }
}

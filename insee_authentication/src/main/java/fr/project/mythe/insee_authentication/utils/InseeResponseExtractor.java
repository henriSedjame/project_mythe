package fr.project.mythe.insee_authentication.utils;

import fr.project.mythe.insee_authentication.api.model.InseeEntreprise;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;

@Component
public class InseeResponseExtractor  implements ResponseExtractor<InseeEntreprise> {

  private InseeResponseConverter converter;

  public InseeResponseExtractor(InseeResponseConverter converter) {
    this.converter = converter;
  }


  @Override
  public InseeEntreprise extractData(ClientHttpResponse clientHttpResponse) throws IOException {
    final String body = clientHttpResponse.getBody().toString();
    return this.converter.convertResponseToEntrepriseObject(body);
  }
}

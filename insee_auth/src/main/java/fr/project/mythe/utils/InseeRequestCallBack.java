package fr.project.mythe.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;

/**
 * @Project sirene
 * @Author Henri Joel SEDJAME
 * @Date 14/10/2018
 * @Class purposes : .......
 */
@Component
public class InseeRequestCallBack implements RequestCallback {
  @Override
  public void doWithRequest(ClientHttpRequest request) throws IOException {
    if (request.getMethod().equals(HttpMethod.POST)) request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
  }
}

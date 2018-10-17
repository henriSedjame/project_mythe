package fr.project.mythe.insee_authentication.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;

import java.io.IOException;

@Component
public class InseeRequestCallBack implements RequestCallback {

  @Override
  public void doWithRequest(ClientHttpRequest request) throws IOException {
    if (request.getMethod().equals(HttpMethod.POST)) request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
  }
}

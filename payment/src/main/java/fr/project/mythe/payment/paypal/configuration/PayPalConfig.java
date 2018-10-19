package fr.project.mythe.payment.paypal.configuration;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource("classpath:paypal_sdk_config.properties")
public class PayPalConfig {
  @Value("${clientID}")
  private String clientId;
  @Value("${secret}")
  private String secret;

  public APIContext getConfig() {
    return new APIContext(clientId, secret, PaypalMode.SANDBOX.name().toLowerCase());
  }
}

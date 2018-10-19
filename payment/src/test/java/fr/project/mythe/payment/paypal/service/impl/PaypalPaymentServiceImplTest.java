package fr.project.mythe.payment.paypal.service.impl;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import fr.project.mythe.payment.paypal.PaymentProvider;
import fr.project.mythe.payment.paypal.configuration.PayPalConfig;
import fr.project.mythe.payment.paypal.service.PaypalPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PaypalPaymentServiceImplTest {

  Payment payment;
  private PaypalPaymentService service;
  @Autowired
  private PayPalConfig config;

  @Before
  public void setUp() {
    service = new PaypalPaymentServiceImpl(config);
    payment = PaymentProvider.createAPayment();
  }

  @Test
  public void notNull() {
    assertAll("not null",
            () -> assertNotNull(payment),
            () -> assertNotNull(service)
    );
  }

  @AfterAll
  public void tearDown() {
  }

  @Test
  public void requestForApprovalURL() throws PayPalRESTException {
    Optional<String> url = service.requestForApprovalURL(this.payment);

    assertNotNull(url);

    log.info("APPROVAL_URL : " + url.get());

  }
}
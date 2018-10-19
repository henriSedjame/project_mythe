package fr.project.mythe.payment.paypal.service.impl;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import fr.project.mythe.payment.paypal.configuration.PayPalConfig;
import fr.project.mythe.payment.paypal.configuration.PaypalConstants;
import fr.project.mythe.payment.paypal.service.PaypalPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PaypalPaymentServiceImpl implements PaypalPaymentService {

  private PayPalConfig config;

  public PaypalPaymentServiceImpl(PayPalConfig config) {
    this.config = config;
  }

  @Override
  public Optional<String> requestForApprovalURL(Payment payment) throws PayPalRESTException {
    Payment createdPayment = payment.create(config.getConfig());
    return createdPayment
            .getLinks()
            .stream()
            .filter(link -> link.getRel().equals(PaypalConstants.APPROVAL_URL))
            .map(link -> link.getHref())
            .findFirst();
  }
}

package fr.project.mythe.payment.paypal.service.impl;

import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.PayPalRESTException;
import fr.project.mythe.payment.paypal.configuration.PayPalConfig;
import fr.project.mythe.payment.paypal.configuration.PaypalConstants;
import fr.project.mythe.payment.paypal.service.PaypalPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class PaypalPaymentServiceImpl implements PaypalPaymentService {

  private PayPalConfig config;

  public PaypalPaymentServiceImpl(PayPalConfig config) {
    this.config = config;
  }

  @Override
  public Optional<Payment> approvePayment(Payment payment) throws PayPalRESTException {

    Payment createdPayment = payment.create(config.getContext());

    if (Objects.isNull(createdPayment)) return Optional.empty();

    return Optional.of(createdPayment);
  }

  @Override
  public Optional<String> requestForApprovalURL(Payment payment) throws PayPalRESTException {

    Optional<Payment> approvedPayment = this.approvePayment(payment);

    if (!approvedPayment.isPresent()) return Optional.empty();

    return approvedPayment
            .get()
            .getLinks()
            .stream()
            .filter(link -> link.getRel().equals(PaypalConstants.APPROVAL_URL))
            .map(link -> link.getHref())
            .findFirst();
  }

  @Override
  public Optional<Payment> executePayment(String paymentId, String payerId) throws PayPalRESTException {

    Payment payment = new Payment().setId(paymentId);

    PaymentExecution execution = new PaymentExecution().setPayerId(payerId);

    Payment executedPayment = payment.execute(this.config.getContext(), execution);

    if (Objects.isNull(executedPayment)) return Optional.empty();

    return Optional.of(executedPayment);
  }
}

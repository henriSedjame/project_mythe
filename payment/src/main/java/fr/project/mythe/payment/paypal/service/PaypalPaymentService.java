package fr.project.mythe.payment.paypal.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.Optional;

public interface PaypalPaymentService {

  /**
   * @param payment
   * @return
   * @throws PayPalRESTException
   */
  Optional<Payment> approvePayment(Payment payment) throws PayPalRESTException;

  /**
   * @param payment
   * @return
   * @throws PayPalRESTException
   */
  Optional<String> requestForApprovalURL(Payment payment) throws PayPalRESTException;

  Optional<Payment> executePayment(String paymentId, String payerId) throws PayPalRESTException;

}

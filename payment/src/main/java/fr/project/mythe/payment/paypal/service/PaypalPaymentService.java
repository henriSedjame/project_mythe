package fr.project.mythe.payment.paypal.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.Optional;

public interface PaypalPaymentService {

  Optional<String> requestForApprovalURL(Payment payment) throws PayPalRESTException;
}

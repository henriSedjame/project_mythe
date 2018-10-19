package fr.project.mythe.payment.paypal;

import com.paypal.api.payments.*;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

public class PaymentProvider {

  /**
   * Methode de création d'un paiement par paypal
   * @return
   */
  public static Payment createAPayment() {

    /**
     *  Créer un montant
     * ajouter la devise
     * ajouter le total
     */
    Amount amount = new Amount();
    amount.setCurrency("USD");
    amount.setTotal("1.00");

    /**
     * Créer une transaction
     * Mettre la transaction dans une liste de transactions
     *
     */
    List<Transaction> transactions = new ArrayList<>();
    Transaction transaction = new Transaction();
    transaction.setAmount(amount);
    transactions.add(transaction);

    /**
     * Créer un payeur
     */
    Payer payer = new Payer();
    PayerInfo payerInfo = new PayerInfo();
    payer.setPaymentMethod("paypal");
    payerInfo.setEmail("sedhjodev-buyer@gmail.com");
    payerInfo.setPayerId("ERTYF");
    payer.setPayerInfo(payerInfo);


    /**
     * Créer les urls de redirection
     * 1 - en cas d'annulation
     * 2 - en cas deretour après paiement
     */
    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl("www.google.com");
    redirectUrls.setReturnUrl("http://localhost:8082/paypal/success");

    /**
     * Créer un paiement
     */
    Payment payment = new Payment();
    payment.setIntent("sale");
    payment.setPayer(payer);
    payment.setTransactions(transactions);
    payment.setRedirectUrls(redirectUrls);

    return payment;

  }

}

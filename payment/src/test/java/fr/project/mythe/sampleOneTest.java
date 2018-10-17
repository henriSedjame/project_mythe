package fr.project.mythe;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.junit.Test;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.List;



public class sampleOneTest {

  enum Mode {
    SANDBOX,
    LIVE;

    public String getName(){
      return this.name().toLowerCase();
    }
  }
  private String clientId = "ARYM-IqNHKOw5CThv6SMWkwUlAc34fLBjiAYAWvGwUS9_mAbhh4Vt6PgS4sOfVDYfJ5EPQjxYX0ZTIFE";
  private String secret ="EIjIMGrKdXwogL0Qyl8Q7u-CP7PnuneBJY6ukpfXh3ySKWF54HzxigIWjYn91083V0lQxfNhrpL7Bpnd";

  /**
   * Methode de création d'un paiement par paypal
   * @return
   */
  public Payment createAPayment(){

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

  @Test
  public void test(){

    try {

      APIContext context= new APIContext(clientId, secret, Mode.SANDBOX.getName());
      Payment payment = this.createAPayment().create(context);
      final String approval_url = payment.getLinks().stream().filter(link -> link.getRel().equals("approval_url")).map(link -> link.getHref()).findFirst().get();


      System.out.println(approval_url);

    } catch (PayPalRESTException e) {
      e.printStackTrace();
    }
  }

}

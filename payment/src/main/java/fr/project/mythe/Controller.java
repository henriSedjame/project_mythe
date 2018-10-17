package fr.project.mythe;

import org.json.JSONObject;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @Project project_mythe
 * @Author Henri Joel SEDJAME
 * @Date 17/10/2018
 * @Class purposes : .......
 */

@RestController
@RequestMapping("/paypal")
public class Controller {

  @GetMapping("/success")
  public String success(HttpServletRequest request){
    final String paymentId = request.getParameter("paymentId");
    final String payerID = request.getParameter("PayerID");
    JSONObject json = new JSONObject();
    json.append("paymentID", paymentId);
    json.append("payerID", payerID);
    return json.toString() ;
  }
}

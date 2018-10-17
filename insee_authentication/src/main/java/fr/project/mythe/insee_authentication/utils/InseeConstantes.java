package fr.project.mythe.insee_authentication.utils;

import org.springframework.beans.factory.annotation.Value;

public class InseeConstantes {

  /** Valeurs paramètres de requêtes pour l'appel de l'api insee **/
  @Value("${insee.entreprises.base-url}")
  public static String BASE_URL_VALUE;
  @Value("${insee.entreprises.token}")
  public static String TOKEN_VALUE;
  @Value("${insee.entreprises.context}")
  public static String CONTEXT_VALUE;
  @Value("${insee.entreprises.recipient}")
  public static String RECIPIENT_VALUE;
  @Value("${insee.entreprises.object}")
  public static String OBJECT_VALUE;

  /** Noms des paramètres de requêtes pour l'appel de l'api insee**/
  public static final String TOKEN = "token";
  public static final String CONTEXT = "context";
  public static final String RECIPIENT = "recipient";
  public static final String OBJECT = "object";
  public static final String SIREN = "siren";
}

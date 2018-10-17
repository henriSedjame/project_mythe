package fr.project.mythe.insee_authentication.api.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class InseeException extends Exception {

  private String message;

  public InseeException(String message) {
    this.message = message;
  }
}

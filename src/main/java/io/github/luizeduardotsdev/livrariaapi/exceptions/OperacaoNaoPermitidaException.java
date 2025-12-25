package io.github.luizeduardotsdev.livrariaapi.exceptions;

public class OperacaoNaoPermitidaException extends RuntimeException {
  public OperacaoNaoPermitidaException(String message) {
    super(message);
  }
}

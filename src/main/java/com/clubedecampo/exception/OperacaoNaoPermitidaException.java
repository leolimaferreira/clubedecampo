package com.clubedecampo.exception;

public class OperacaoNaoPermitidaException extends RuntimeException{
  public OperacaoNaoPermitidaException(String message) {
    super(message);
  }
}

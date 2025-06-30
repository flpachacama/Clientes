package com.banquito.cores.clientes.exception;

public class ArgumentoIlegalException extends RuntimeException {

  public ArgumentoIlegalException(String mensaje) {
    super(mensaje);
  }

  public ArgumentoIlegalException(String mensaje, Throwable causa) {
    super(mensaje, causa);
  }
}
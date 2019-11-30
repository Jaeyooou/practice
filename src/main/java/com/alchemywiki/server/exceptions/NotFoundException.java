package com.alchemywiki.server.exceptions;


public class NotFoundException extends RuntimeException {
  public NotFoundException(Class<?> clazz, Object key) {
    super(String.format("Can not find %s with %s.", clazz.getSimpleName(), key));
  }
}

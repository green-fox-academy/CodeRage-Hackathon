package com.hackathon.coderage.toolboxproject.exceptions;

public class IncorrectIdException extends Exception {

  public IncorrectIdException() {
    super("Id doesn't exist in the database.");
  }
}

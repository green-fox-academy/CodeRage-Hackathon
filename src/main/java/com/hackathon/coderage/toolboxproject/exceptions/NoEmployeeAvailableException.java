package com.hackathon.coderage.toolboxproject.exceptions;

public class NoEmployeeAvailableException extends Exception {

  public NoEmployeeAvailableException() {
    super("No one is available that day.");
  }
}

package com.hackathon.coderage.toolboxproject.exceptions;

public class NoToolAvailableException extends Exception {

  public NoToolAvailableException() {
    super("No tool is available that day.");
  }
}

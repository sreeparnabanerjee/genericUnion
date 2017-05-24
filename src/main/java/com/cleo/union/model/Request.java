package com.cleo.union.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sreeparna on 23/05/17.
 */
public class Request {
  private Map<String, Integer> input;

  public Request(HashMap<String, Integer> input) {
    this.input = input;
  }

  public Request() {
  }

  public Map<String, Integer> getInput() {
    return input;
  }

  public void setInput(Map<String, Integer> input) {
    this.input = input;
  }
}

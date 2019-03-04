package com.apple.flagchecker.domain;

import com.apple.flagchecker.entity.Continent;
import java.util.List;

public class Response {

  private List<Continent> continents;
  private List<ApiError> errors;

  public List<Continent> getContinents() {
    return continents;
  }

  public void setContinents(List<Continent> continents) {
    this.continents = continents;
  }

  public List<ApiError> getErrors() {
    return errors;
  }

  public void setErrors(List<ApiError> errors) {
    this.errors = errors;
  }
}

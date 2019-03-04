package com.apple.flagchecker.domain;

import java.util.Set;

public class Request {

  private Set<String> continents;
  private Set<String> countries;

  public Request(Builder builder) {
    this.continents = builder.continents;
    this.countries = builder.countries;
  }

  public static Builder builder() {
    Builder builder = new Builder();
    return builder;
  }

  public Set<String> getContinents() {
    return continents;
  }

  public Set<String> getCountries() {
    return countries;
  }

  public static class Builder {

    Set<String> continents;
    Set<String> countries;

    private Builder() {
    }

    public Builder withContinents(Set<String> continents) {
      this.continents = continents;
      return this;
    }

    public Builder withCountries(Set<String> countries) {
      this.countries = countries;
      return this;
    }

    /**
     * @return an instance of RequestContext using the builder data.
     */
    public Request build() {
      return new Request(this);
    }

  }


}

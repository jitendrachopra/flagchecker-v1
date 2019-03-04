package com.apple.flagchecker.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "continent")
@NamedQueries({
    @NamedQuery(
        name = "getAllContinents",
        query = "from Continent c"
    ),
    @NamedQuery(
        name = "findContinents",
        query = "from Continent c where c.name IN :name"
    ),
    @NamedQuery(
        name = "getAllContinentsById",
        query = "from Continent c where c.id IN :id"
    )
})
public class Continent extends AbstractEntity {

  @Column(unique = true)
  @JsonProperty("continent")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "continent_id")
  private List<Country1> countries = new ArrayList<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Country1> getCountries() {
    return countries;
  }

  public void setCountries(List<Country1> countries) {
    this.countries = countries;
  }
}

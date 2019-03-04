package com.apple.flagchecker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "country")
@NamedQueries({
    @NamedQuery(
        name = "findCountries",
        query = "from Country1 c where c.name IN :name"
    ),
})
//Need to give Country1 since my id has issue. It should "Country" instead.
public class Country1 extends AbstractEntity {

  @Column(unique = true, name = "name")
  private String name;
  @Column(unique = true, name = "flag_url")
  private String flag;
  @JsonIgnore
  @Column(name = "continent_id")
  private Long continent_id;

  public Country1() {
  }

  public Country1(String name, String flag, Long continent_id) {
    this.name = name;
    this.flag = flag;
    this.continent_id = continent_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public Long getContinent_id() {
    return continent_id;
  }

  public void setContinent_id(Long continent_id) {
    this.continent_id = continent_id;
  }

}

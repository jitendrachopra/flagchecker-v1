package com.apple.flagchecker.service.impl;


import com.apple.flagchecker.dao.ContinentDAO;
import com.apple.flagchecker.dao.CountryDAO;
import com.apple.flagchecker.domain.Request;
import com.apple.flagchecker.entity.Continent;
import com.apple.flagchecker.entity.Country1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class FlagServiceImplTest {

  @Mock
  private FlagServiceImpl flagService;

  @Mock
  private ContinentDAO continentDAO;

  @Mock
  private CountryDAO countryDAO;


  @Before
  public void setup() {

    flagService = new FlagServiceImpl();
    flagService.setContinentDAO(continentDAO);
    flagService.setCountryDAO(countryDAO);

  }

  @Test
  public void withContinents() {
    List<Continent> continents = new ArrayList<>();
    Continent continent = new Continent();
    continent.setName("Africa");

    List<Country1> countries = new ArrayList<>();
    countries.add(new Country1("Nigeria", "http://bit.yl", new Long(1)));
    continent.setCountries(countries);
    continents.add(continent);

    Set<String> continentsSet = new HashSet<>();
    continentsSet.add("Africa");

    Mockito.when(continentDAO.getContinentWithAllCountries(continentsSet)).thenReturn(continents);

    Request request = Request.builder().withContinents(continentsSet)
        .build();//.withContinents(continentsSet).build();
    List<Continent> continentsResult = flagService.getFlags(request);
    Assert.assertEquals(continentsResult, continents);

  }

  @Test
  public void withCountries() {
    List<Continent> continents = new ArrayList<>();
    Continent continent = new Continent();
    continent.setName("Africa");

    List<Country1> countries = new ArrayList<>();
    countries.add(new Country1("Nigeria", "http://bit.yl", new Long(1)));
    continent.setCountries(countries);
    continents.add(continent);
    Set<String> countriesSet = new HashSet<>();
    countriesSet.add("Nigeria");

    Set<Long> continentIds = new HashSet<>();
    continentIds.add(new Long(1));

    Mockito.when(countryDAO.getCountries(countriesSet)).thenReturn(countries);
    Mockito.when(continentDAO.getContinentById(continentIds)).thenReturn(continents);

    Request request = Request.builder().withCountries(countriesSet)
        .build();//.withContinents(continentsSet).build();
    List<Continent> continentsResult = flagService.getFlags(request);
    Assert.assertEquals(continentsResult.get(0).getCountries().get(0),
        continents.get(0).getCountries().get(0));

  }

  @Test
  public void allFlags() {
    List<Continent> continents = new ArrayList<>();
    Continent continent = new Continent();
    continent.setName("Africa");

    List<Country1> countries = new ArrayList<>();
    countries.add(new Country1("Nigeria", "http://bit.yl", new Long(1)));
    continent.setCountries(countries);
    continents.add(continent);

    Mockito.when(continentDAO.getAllContinentWithAllCountries()).thenReturn(continents);

    Request request = Request.builder().build();
    List<Continent> continentsResult = flagService.getFlags(request);
    Assert.assertEquals(continentsResult, continents);

  }
}

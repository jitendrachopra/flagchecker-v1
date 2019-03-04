package com.apple.flagchecker.dao.impl;


import com.apple.flagchecker.entity.Continent;
import com.apple.flagchecker.entity.Country1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ContinentDAOImplTest {

  @Mock
  private ContinentDAOImpl continentDAO;

  @Mock
  private SessionFactory mockedSessionFactory;

  @Mock
  Session mockedSession;

  @Mock
  Query query;

  @Mock
  Transaction mockedTransaction;

  List<Continent> continents;

  @Before
  public void setup() {

    continents = new ArrayList<>();
    Continent continent = new Continent();
    continent.setName("Africa");

    List<Country1> countries = new ArrayList<>();
    countries.add(new Country1("Nigeria", "http://bit.yl", new Long(1)));
    continent.setCountries(countries);
    continents.add(continent);
    continentDAO = new ContinentDAOImpl();
    continentDAO.setSessionFactory(mockedSessionFactory);

    mockedSessionFactory = Mockito.mock(SessionFactory.class);
    mockedSession = Mockito.mock(Session.class);
    query = Mockito.mock(Query.class);
    mockedTransaction = Mockito.mock(Transaction.class);
    Mockito.when(mockedSessionFactory.getCurrentSession()).thenReturn(mockedSession);
    Mockito.when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
    Mockito.when(mockedSession.getNamedQuery(Matchers.anyString())).thenReturn(query);
    Mockito.when(query.getResultList()).thenReturn(continents);
    continentDAO.setSessionFactory(this.mockedSessionFactory);

  }

  @Test
  public void getAllContinentWithAllCountries() {
    Assert.assertNotNull(continentDAO.getAllContinentWithAllCountries());
  }

  @Test
  public void getContinentWithAllCountries() {
    Set<String> continentsSet = new HashSet<>();
    continentsSet.add("Africa");
    Assert.assertNotNull(continentDAO.getContinentWithAllCountries(continentsSet));
  }

  @Test
  public void getContinentById() {
    Set<Long> continentIds = new HashSet<>();
    continentIds.add(new Long(1));
    Assert.assertNotNull(continentDAO.getContinentById(continentIds));
  }
}

package com.apple.flagchecker.dao.impl;

import com.apple.flagchecker.dao.ContinentDAO;
import com.apple.flagchecker.entity.Continent;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContinentDAOImpl implements ContinentDAO {


  @Autowired
  private SessionFactory sessionFactory;

  public List<Continent> getAllContinentWithAllCountries() {

    Session session = sessionFactory.getCurrentSession();
    TypedQuery<Continent> query = session.getNamedQuery("getAllContinents");

    return query.getResultList();
  }

  public List<Continent> getContinentWithAllCountries(Set<String> continents) {

    Session session = sessionFactory.getCurrentSession();
    TypedQuery<Continent> query = session.getNamedQuery("findContinents");

    query.setParameter("name", continents);
    return query.getResultList();

  }

  @Override
  public List<Continent> getContinentById(Set<Long> ids) {
    Session session = this.sessionFactory.getCurrentSession();
    TypedQuery<Continent> query = session.getNamedQuery("getAllContinentsById");

    query.setParameter("id", ids);
    return query.getResultList();
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}

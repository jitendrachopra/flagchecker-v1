package com.apple.flagchecker.dao.impl;

import com.apple.flagchecker.dao.CountryDAO;
import com.apple.flagchecker.entity.Country1;
import java.util.List;
import java.util.Set;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAOImpl implements CountryDAO {


  @Autowired
  private SessionFactory sessionFactory;


  @Override
  public List<Country1> getCountries(Set<String> countries) {
    Session session = sessionFactory.getCurrentSession();
    TypedQuery<Country1> query = session.getNamedQuery("findCountries");

    query.setParameter("name", countries);
    return query.getResultList();
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}

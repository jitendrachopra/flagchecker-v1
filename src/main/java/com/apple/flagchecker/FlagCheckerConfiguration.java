
package com.apple.flagchecker;

import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Applications Data Configuration to load datasource
 */

@Configuration
@EntityScan("com.apple.flagchecker.entity")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class FlagCheckerConfiguration {

  @Value("${app.datasource.driverClassName}")
  String driverClassName;
  @Value("${app.datasource.url}")
  String url;
  @Value("${app.datasource.username}")
  String username;
  @Value("${app.datasource.password}")
  String password;

  //TODO DB Connection Pool

  /**
   * Connect to MySql Data base..
   *
   * @return @{DataSource}
   */
  @Bean(name = "dataSource")
  public DataSource getDataSource() {
    DataSource dataSource = DataSourceBuilder
        .create()
        .username(username)
        .password(password)
        .url(url)
        .driverClassName(driverClassName)
        .build();
    return dataSource;
  }

  @Bean(name = "sessionFactory")
  public SessionFactory getSessionFactory(DataSource dataSource) {
    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    sessionBuilder.scanPackages("com.apple.flagchecker.entity");
    return sessionBuilder.buildSessionFactory();
  }

  @Bean(name = "transactionManager")
  public HibernateTransactionManager getTransactionManager(
      SessionFactory sessionFactory) {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
        sessionFactory);
    return transactionManager;
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
    final DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    return initializer;
  }

}


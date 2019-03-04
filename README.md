# Description

How to setup Project
************************************************************************************************************************

Required
1) Setup Database script and data mentioned in database folder

  app.datasource.url = jdbc:mysql://DATABASEHOST:3306/flagchecker?useSSL=false
  app.datasource.username = username
  app.datasource.password = password

Run FlagCheckerApplication.java

************************************************************************************************************************
Service Endpoint
http://localhost:8080/v1/flags
http://localhost:8080/v1/flags?countries=India,China
http://localhost:8080/v1/flags?continents=Africa,America
http://localhost:8080/v1/flags?countries=India,China&continents=Africa,America


************************************************************************************************************************
Metrics Endpoint
All Metrics Endpoint can be found here
http://localhost:8080/actuator/
http://localhost:8080/actuator/info
http://localhost:8080/actuator/health

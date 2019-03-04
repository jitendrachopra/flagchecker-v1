package com.apple.flagchecker.service.impl;

import com.apple.flagchecker.dao.ContinentDAO;
import com.apple.flagchecker.dao.CountryDAO;
import com.apple.flagchecker.domain.Request;
import com.apple.flagchecker.entity.Continent;
import com.apple.flagchecker.entity.Country1;
import com.apple.flagchecker.service.FlagService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class FlagServiceImpl implements FlagService {

  @Autowired
  private ContinentDAO continentDAO;

  @Autowired
  private CountryDAO countryDAO;

  @Override
  public List<Continent> getFlags(Request request) {

    List<Continent> continentsResult = new ArrayList<>();

    if (CollectionUtils.isEmpty(request.getContinents()) && CollectionUtils
        .isEmpty(request.getCountries())) {

      continentsResult = continentDAO.getAllContinentWithAllCountries();

    } else if (!CollectionUtils.isEmpty(request.getContinents()) && CollectionUtils
        .isEmpty(request.getCountries())) {

      continentsResult = continentDAO.getContinentWithAllCountries(request.getContinents());

    } else if (CollectionUtils.isEmpty(request.getContinents()) && !CollectionUtils
        .isEmpty(request.getCountries())) {

      List<Country1> countries = countryDAO.getCountries(request.getCountries());
      Set<String> countryNames = new HashSet<>();
      Set<Long> continentIds = new HashSet<>();
      for (Country1 country1 : countries) {
        continentIds.add(country1.getContinent_id());
        countryNames.add(country1.getName());
      }

      List<Continent> continentsResultByIds = continentDAO.getContinentById(continentIds);

      continentsResult = process(continentsResultByIds, countryNames);
    }

    return continentsResult;
  }

  public void setContinentDAO(ContinentDAO continentDAO) {
    this.continentDAO = continentDAO;
  }

  public void setCountryDAO(CountryDAO countryDAO) {
    this.countryDAO = countryDAO;
  }

  private List<Continent> process(List<Continent> continentsResultByIds, Set<String> countryNames) {
    List<Continent> continentsResult = new ArrayList<>();

    for (Continent continent : continentsResultByIds) {
      Continent continentNew = new Continent();
      continentNew.setName(continent.getName());
      List<Country1> country1List = new ArrayList<>();
      for (Country1 countryTemp : continent.getCountries()) {
        if (countryNames.contains(countryTemp.getName())) {
          country1List.add(countryTemp);
        }
      }
      continentNew.setCountries(country1List);
      continentsResult.add(continentNew);
    }
    return continentsResult;
  }
}

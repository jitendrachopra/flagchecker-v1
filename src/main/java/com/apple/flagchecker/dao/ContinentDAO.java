package com.apple.flagchecker.dao;

import com.apple.flagchecker.entity.Continent;
import java.util.List;
import java.util.Set;

public interface ContinentDAO {

  List<Continent> getAllContinentWithAllCountries();

  List<Continent> getContinentWithAllCountries( Set<String> continents);

  List<Continent> getContinentById( Set<Long> ids);

}

package com.apple.flagchecker.dao;

import com.apple.flagchecker.entity.Country1;
import java.util.List;
import java.util.Set;

public interface CountryDAO {


  List<Country1> getCountries(Set<String> countries);

}

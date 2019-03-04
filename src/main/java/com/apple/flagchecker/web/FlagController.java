package com.apple.flagchecker.web;


import com.apple.flagchecker.domain.ApiError;
import com.apple.flagchecker.domain.ExceptionType;
import com.apple.flagchecker.domain.Request;
import com.apple.flagchecker.metrics.FlagCheckMetrics.FlagCheckMetrics;
import com.apple.flagchecker.service.FlagService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlagController {

  public static final String BASE_PATH = "v1";
  public static final String FLAGS_PATH = "/flags";
  public Logger logger = LoggerFactory.getLogger("FlagController");

  @Autowired
  FlagCheckMetrics flagCheckMetrics;

  @Autowired
  private FlagService flagService;

  @RequestMapping(path = BASE_PATH + FLAGS_PATH, method = RequestMethod.GET)
  public ResponseEntity<Object> getFlags(
      @RequestParam(value = "continents", required = false) String continents,
      @RequestParam(value = "countries", required = false) String countries) {

    Set<String> continentSet = null;
    Set<String> countriesSet = null;
    if (StringUtils.isEmpty(continents) && StringUtils.isEmpty(countries)) {
      flagCheckMetrics.increaseallCountriesCount();
    } else {
      if (!StringUtils.isEmpty(continents)) {
        continentSet = new HashSet<>(Arrays.asList(continents.split(",")));
        flagCheckMetrics.increaserequestByContinentCounter();
      }
      if (!StringUtils.isEmpty(countries)) {
        countriesSet = new HashSet<>(Arrays.asList(countries.split(",")));
        flagCheckMetrics.increaserequestByCountryCounter();
      }
      logger.info("Requesting Countries {} Continents {} ", countriesSet, continentSet);
      if (countriesSet != null && continentSet != null) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
            "Either Continents or Country need to requested", ExceptionType.BUSINESS, "400");
        flagCheckMetrics.increaseerrorCount();
        return new ResponseEntity<>(apiError, apiError.getStatus());
      }
    }

    Request request = Request.builder().
        withContinents(continentSet).
        withCountries(countriesSet)
        .build();

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    //Cache Setup
    //headers.put("cache-control", Arrays.asList("private, max-age=604800"));

    return new ResponseEntity<>(flagService.getFlags(request), headers, HttpStatus.OK);
  }

}

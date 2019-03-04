package com.apple.flagchecker.metrics.FlagCheckMetrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.stereotype.Component;

/**
 * Take Metrics and increment Counter This can be viewed at actuator
 */
@Component
public class FlagCheckMetrics {

  SimpleMeterRegistry registry = new SimpleMeterRegistry();

  Counter requestByCountryCounter = Counter
      .builder("RequestByCountry")
      .description("RequestByCountry")
      .tags("prod", "performance")
      .register(registry);

  Counter requestByContinentCounter = Counter
      .builder("requestByContinentCounter")
      .description("requestByContinentCounter")
      .tags("prod", "performance")
      .register(registry);

  Counter errorCount = Counter
      .builder("error")
      .description("error")
      .tags("prod", "performance")
      .register(registry);

  Counter allCountriesCount = Counter
      .builder("allCountriesCount")
      .description("allCountriesCount")
      .tags("prod", "performance")
      .register(registry);

  public void increaserequestByCountryCounter() {
    requestByCountryCounter.increment(1);
  }

  public void increaserequestByContinentCounter() {
    requestByContinentCounter.increment(1);
  }

  public void increaseerrorCount() {
    errorCount.increment(1);
  }

  public void increaseallCountriesCount() {
    allCountriesCount.increment(1);
  }
}

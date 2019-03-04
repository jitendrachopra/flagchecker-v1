package com.apple.flagchecker;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Performing integration test to valid if endpoint work as expected.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlagControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  //TODO assertion on Content
  @Test
  public void noParamShouldReturnAllCountries() throws Exception {

    this.mockMvc.perform(get("/v1/flags")).andDo(print()).andExpect(status().isOk());
  }

  @Test
  public void onlyCountries() throws Exception {

    this.mockMvc.perform(get("/v1/flags?countries=India,China")).andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void onlyContinents() throws Exception {

    this.mockMvc.perform(get("/v1/flags?continents=Africa")).andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void bothContinentsAndCountry() throws Exception {

    this.mockMvc.perform(get("/v1/flags?continents=Africa&countries=India,China")).andDo(print())
        .andExpect(status().isBadRequest());
  }

}

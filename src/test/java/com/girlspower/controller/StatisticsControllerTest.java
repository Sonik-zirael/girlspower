package com.girlspower.controller;

import com.girlspower.domain.Statistics;
import com.girlspower.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-for-statistics-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD )
@Sql(value = {"/delete-after-statistics.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("a")
public class StatisticsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void statisticsPageTest() throws Exception {
        List<Statistics> statistics = statisticsService.getStatistics();
        Float[] weightArray = new Float[statistics.size()];
        Float[] heightArray = new Float[statistics.size()];
        for (int i = 0; i < statistics.size(); i++) {
            weightArray[i] = statistics.get(i).getWeight();
            heightArray[i] = statistics.get(i).getHeight();
        }
        this.mockMvc.perform(get("/statistics"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(weightArray[0].toString())))
                .andExpect(content().string(containsString(heightArray[0].toString())));

    }

}

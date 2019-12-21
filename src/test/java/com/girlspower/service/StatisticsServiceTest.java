package com.girlspower.service;

import com.girlspower.domain.Statistics;
import com.girlspower.domain.User;
import com.girlspower.repos.StatisticsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-for-statistics-test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-after-statistics.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("a")
public class StatisticsServiceTest {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void testIfStatisticsUpdatesCorrectly() throws Exception {
        String stringDate = "20/12/2019";
        Date testDate = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
        User user = userInfoService.findByAuthentication();
        Statistics stat = statisticsRepository.findByOwnerAndDate(user, testDate);
        float weightBefore = stat.getWeight();
        float heightBefore = stat.getHeight();
        float newWeight = weightBefore + 0.5F;
        float newHeight = heightBefore - 0.5F;
        statisticsService.updateStatistics(user, newWeight, newHeight, testDate);
        Statistics newStat = statisticsRepository.findByOwnerAndDate(user, testDate);
        assertThat(newWeight, is(newStat.getWeight()));
        assertThat(newHeight, is(newStat.getHeight()));
        assertThat(weightBefore, is(newStat.getWeight() - 0.5F));
    }

    @Test
    public void testIfWeCanGetAllUserStatistics() throws Exception {
        List<Statistics> statisticsList = statisticsService.getStatistics();
        assertThat(statisticsList, hasSize(3));
        List<Float> weights = new ArrayList<>();
        for (Statistics s : statisticsList) {
            weights.add(s.getWeight());
        }
        assertThat(weights, containsInAnyOrder(55.0F, 56.0F, 57.0F));
    }

    @Test
    public void testCorrectnessOfWorstUsersFinding() throws Exception {
        assertThat(statisticsService.getWorstUsers(), is("1"));
    }

}

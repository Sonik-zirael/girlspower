package com.girlspower.service;

import com.girlspower.domain.Statistics;
import com.girlspower.domain.User;
import com.girlspower.repos.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final UserInfoService userInfoService;

    public StatisticsService(StatisticsRepository statisticsRepository, UserInfoService userInfoService) {
        this.statisticsRepository = statisticsRepository;
        this.userInfoService = userInfoService;
    }

    public void updateStatistics(User user, float weight, float height) {
        Date date = new Date();
        Statistics statistics;
        if (statisticsRepository.findByOwnerAndDate(user, date) != null) {
            statistics = statisticsRepository.findByOwnerAndDate(user, date);
            float epsilon = 0.001f;
            boolean isWeightEqual = Math.abs(statistics.getWeight() - weight) <= epsilon;
            if (!isWeightEqual) {
                statistics.setWeight(weight);
            }
            boolean isHeightEqual = Math.abs(statistics.getHeight() - height) <= epsilon;
            if (!isHeightEqual) {
                statistics.setHeight(height);
            }
        } else {
            statistics = new Statistics(date, weight, height, user);
        }
        statisticsRepository.save(statistics);
    }

    public List<Statistics> getStatistics() {
        User user = userInfoService.findByAuthentication();
        return statisticsRepository.findByOwner(user);
    }
}

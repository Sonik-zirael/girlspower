package com.girlspower.service;

import com.girlspower.domain.Statistics;
import com.girlspower.domain.User;
import com.girlspower.repos.StatisticsRepository;
import com.girlspower.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final UserInfoService userInfoService;
    private final UserRepository userRepository;

    public StatisticsService(StatisticsRepository statisticsRepository, UserInfoService userInfoService, UserRepository userRepository) {
        this.statisticsRepository = statisticsRepository;
        this.userInfoService = userInfoService;
        this.userRepository = userRepository;
    }

    public void updateStatistics(User user, float weight, float height, Date date) {
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

    public String getWorstUsers() {
        User user = userInfoService.findByAuthentication();
        double NORMA = 21.5;
        double userBMI = user.getInfo().getWeight() / Math.pow(user.getInfo().getHeight(), 2);
        List<User> otherUsers = userRepository.findAll();
        Integer count = 0;
        for (User u : otherUsers) {
            double BMI = u.getInfo().getWeight() / Math.pow(u.getInfo().getHeight(), 2);
            if (Math.abs(NORMA - userBMI) < Math.abs(NORMA - BMI)) {
                count++;
            }
        }
        return count.toString();
    }
}

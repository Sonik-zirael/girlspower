package com.girlspower.controller;

import com.girlspower.domain.Statistics;
import com.girlspower.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class StatisticsController {
    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistics")
    public String showStatistics(Map<String, Object> model) {
        List<Statistics> statistics = statisticsService.getStatistics();
        Date[] dateArray = new Date[statistics.size()];
        float[] weightArray = new float[statistics.size()];
        float[] heightArray = new float[statistics.size()];
        float[] BMIArray = new float[statistics.size()];
        for (int i = 0; i < statistics.size(); i++) {
            dateArray[i] = statistics.get(i).getDate();
            weightArray[i] = statistics.get(i).getWeight();
            heightArray[i] = statistics.get(i).getHeight();
            BMIArray[i] = weightArray[i] / (float)Math.pow(heightArray[i], 2);
        }
        model.put("dates", dateArray);
        model.put("weights", weightArray);
        model.put("BMI", BMIArray);
        return "statistics";
    }
}

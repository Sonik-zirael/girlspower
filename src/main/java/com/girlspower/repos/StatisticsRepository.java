package com.girlspower.repos;

import com.girlspower.domain.Statistics;
import com.girlspower.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Statistics findByOwnerAndDate(User owner, Date date);
    List<Statistics> findByOwner(User owner);
}

package com.girlspower.service;

import com.girlspower.domain.Tips;
import com.girlspower.repos.TipsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TipsService {
    private final TipsRepository tipsRepository;

    public TipsService(TipsRepository tipsRepository) {
        this.tipsRepository = tipsRepository;
    }

    public Tips getTodayAdvice() {
        int randSize = tipsRepository.findAll().size();
        Random rnd = new Random(System.currentTimeMillis());
        long randIndex = rnd.nextInt(randSize + 1);
        Optional<Tips> randTip = tipsRepository.findById(randIndex);
        return randTip.orElseGet(Tips::new);
    }
}

package com.game.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculateLevelServiceBean implements CalculateLevelService {

    @Override
    public Integer calculateLevel(Integer experience) {
        return BigDecimal.valueOf(Math.sqrt(2500 + 200 * experience) - 50).divide(BigDecimal.valueOf(100), RoundingMode.DOWN).intValue();
    }

    @Override
    public Integer calculateUntilNextLevel(Integer level, Integer experience) {
        return 50 * (level + 1) * (level + 2) - experience;
    }
}

package com.game.service;

public interface CalculateLevelService {
    Integer calculateLevel(Integer experience);
    Integer calculateUntilNextLevel(Integer level, Integer experience);
}

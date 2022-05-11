package com.game.service;

import com.game.entity.Profession;
import com.game.entity.Race;

public interface GetPlayerCountService {
    Long getPlayerCount(String name,
                        String title,
                        Race race,
                        Profession profession,
                        Long after,
                        Long before,
                        Boolean banned,
                        Integer minExperience,
                        Integer maxExperience,
                        Integer minLevel,
                        Integer maxLevel);
}

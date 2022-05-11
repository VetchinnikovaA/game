package com.game.service;

import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class GetPlayerCountServiceBean implements GetPlayerCountService {

    private final PlayerRepository playerRepository;

    public GetPlayerCountServiceBean(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Long getPlayerCount(String name, String title, Race race, Profession profession, Long after, Long before, Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel) {
        return playerRepository.count(PlayerRepository.getSpecification(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel));
    }
}

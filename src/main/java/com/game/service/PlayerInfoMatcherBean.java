package com.game.service;

import com.game.entity.PlayerEntity;
import com.game.entity.PlayerInfo;
import org.springframework.stereotype.Service;

@Service
public class PlayerInfoMatcherBean implements PlayerInfoMatcher {

    private final CalculateLevelService calculateLevelService;

    public PlayerInfoMatcherBean(CalculateLevelService calculateLevelService) {
        this.calculateLevelService = calculateLevelService;
    }

    @Override
    public PlayerEntity apply(PlayerEntity playerEntity, PlayerInfo playerInfo) {
        playerEntity.setName(playerInfo.getName() == null ? playerEntity.getName() : playerInfo.getName());
        playerEntity.setTitle(playerInfo.getTitle() == null ? playerEntity.getTitle() : playerInfo.getTitle());
        playerEntity.setRace(playerInfo.getRace() == null ? playerEntity.getRace() : playerInfo.getRace());
        playerEntity.setProfession(playerInfo.getProfession() == null ? playerEntity.getProfession() : playerInfo.getProfession());
        playerEntity.setBanned(playerInfo.getBanned() == null ? playerEntity.getBanned() : playerInfo.getBanned());
        if (playerInfo.getBirthday() != null)
            playerEntity.setBirthday(playerInfo.getBirthday());
        if (playerInfo.getExperience() != null && !playerInfo.getExperience().equals(playerEntity.getExperience())) {
            playerEntity.setExperience(playerInfo.getExperience());
            Integer level = calculateLevelService.calculateLevel(playerInfo.getExperience());
            playerEntity.setLevel(level);
            playerEntity.setUntilNextLevel(calculateLevelService.calculateUntilNextLevel(level, playerInfo.getExperience()));
        }
        return playerEntity;
    }
}

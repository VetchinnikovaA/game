package com.game.service;

import com.game.entity.PlayerEntity;
import com.game.entity.PlayerInfo;
import org.springframework.stereotype.Service;

@Service
public class PlayerEntityMapperBean implements PlayerEntityMapper {

    private final CalculateLevelService calculateLevelService;

    public PlayerEntityMapperBean(CalculateLevelService calculateLevelService) {
        this.calculateLevelService = calculateLevelService;
    }

    @Override
    public PlayerEntity apply(PlayerInfo player) {
        Integer level = calculateLevelService.calculateLevel(player.getExperience());
        Integer untilNextLevel = calculateLevelService.calculateUntilNextLevel(level, player.getExperience());

        return PlayerEntity.builder()
                .name(player.getName())
                .title(player.getTitle())
                .birthday(player.getBirthday())
                .race(player.getRace())
                .profession(player.getProfession())
                .experience(player.getExperience())
                .banned(player.getBanned() == null ? false : player.getBanned())
                .level(level)
                .untilNextLevel(untilNextLevel)
                .build();
    }
}

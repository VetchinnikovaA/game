package com.game.service;

import com.game.entity.PlayerEntity;
import com.game.entity.PlayerInfo;
import org.springframework.stereotype.Service;

@Service
public class PlayerMapperBean implements PlayerMapper {

    @Override
    public PlayerInfo apply(PlayerEntity playerEntity) {
        return PlayerInfo.builder()
                .id(playerEntity.getId())
                .name(playerEntity.getName())
                .title(playerEntity.getTitle())
                .birthday(playerEntity.getBirthday())
                .race(playerEntity.getRace())
                .profession(playerEntity.getProfession())
                .experience(playerEntity.getExperience())
                .banned(playerEntity.getBanned())
                .level(playerEntity.getLevel())
                .untilNextLevel(playerEntity.getUntilNextLevel())
                .build();
    }
}

package com.game.service;

import com.game.entity.PlayerEntity;
import com.game.entity.PlayerInfo;

import java.util.function.Function;

public interface PlayerEntityMapper extends Function<PlayerInfo, PlayerEntity> {
}

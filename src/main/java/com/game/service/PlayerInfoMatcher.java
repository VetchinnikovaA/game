package com.game.service;

import com.game.entity.PlayerEntity;
import com.game.entity.PlayerInfo;

import java.util.function.BiFunction;

public interface PlayerInfoMatcher extends BiFunction<PlayerEntity, PlayerInfo, PlayerEntity> {
}

package com.game.service;

import com.game.entity.PlayerInfo;

public interface UpdatePlayerService {

    PlayerInfo updateById(Long id, PlayerInfo player);
}

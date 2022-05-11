package com.game.service;

import com.game.entity.PlayerEntity;
import com.game.entity.PlayerInfo;
import com.game.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional
public class CreatePlayerServiceBean implements CreatePlayerService {

    private final PlayerRepository playerRepository;

    private final PlayerEntityMapper playerEntityMapper;

    private final PlayerMapper playerMapper;

    public CreatePlayerServiceBean(PlayerRepository playerRepository, PlayerEntityMapper playerEntityMapper, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerEntityMapper = playerEntityMapper;
        this.playerMapper = playerMapper;
    }

    @Override
    public PlayerInfo create(PlayerInfo request) {
        return Optional.ofNullable(request)
                .map(player -> {
                    PlayerEntity playerEntity = playerEntityMapper.apply(player);
                    playerRepository.save(playerEntity);
                    return playerEntity;
                })
                .map(playerMapper)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "request must be not null"));
    }
}

package com.game.service;

import com.game.entity.PlayerInfo;
import com.game.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class GetPlayerServiceBean implements GetPlayerService {

    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    public GetPlayerServiceBean(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public PlayerInfo getById(Long id) {
        return Optional.ofNullable(id)
                .filter(playerId -> playerId > 0)
                .map(playerId -> playerRepository.findById(playerId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Player Not Found")))
                .map(playerMapper)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Id must be more 0"));
    }
}

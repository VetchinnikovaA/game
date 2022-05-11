package com.game.service;

import com.game.entity.PlayerInfo;
import com.game.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional
public class UpdatePlayerServiceBean implements UpdatePlayerService {

    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    private final PlayerInfoMatcher playerInfoMatcher;

    public UpdatePlayerServiceBean(PlayerRepository playerRepository, PlayerMapper playerMapper, PlayerInfoMatcher playerInfoMatcher) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.playerInfoMatcher = playerInfoMatcher;
    }

    @Override
    public PlayerInfo updateById(Long id, PlayerInfo playerInfo) {
        return Optional.ofNullable(id)
                .filter(playerId -> playerId > 0)
                .map(playerId -> playerRepository.findById(playerId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Player Not Found")))
                .map(playerEntity -> playerInfoMatcher.apply(playerEntity, playerInfo))
                .map(playerMapper)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Id must be more 0"));
    }
}

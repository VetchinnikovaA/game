package com.game.service;

import com.game.repository.PlayerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@Transactional
public class DeletePlayerServiceBean implements DeletePlayerService {

    private final PlayerRepository playerRepository;

    public DeletePlayerServiceBean(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void deleteById(Long id) {
        Optional.ofNullable(id)
                .filter(playerId -> playerId > 0)
                .orElseThrow(() -> new ResponseStatusException(
                        BAD_REQUEST, "Id must be more 0"));

        try {
            playerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Player Not Found");
        }
    }
}

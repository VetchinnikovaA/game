package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.PlayerInfo;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPlayersServiceBean implements GetPlayersService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public GetPlayersServiceBean(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Override
    public List<PlayerInfo> getPlayers(String name, String title, Race race, Profession profession, Long after, Long before, Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel, PlayerOrder order, Integer pageNumber, Integer pageSize) {
        return playerRepository.findAll(PlayerRepository.getSpecification(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel), PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())))
                .map(playerMapper).getContent();
    }
}

package com.game.controller;

import com.game.entity.CreatePlayerRequest;
import com.game.entity.PlayerInfo;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PlayerController {

    private final GetPlayersService getPlayersService;

    private final CreatePlayerService createPlayerService;

    private final GetPlayerService getPlayerService;

    private final GetPlayerCountServiceBean getPlayerCountService;

    private final UpdatePlayerService updatePlayerService;

    private final DeletePlayerService deletePlayerService;

    private final Validator createPlayerRequestValidator;

    private final Validator playerInfoValidator;

    public PlayerController(GetPlayersService getPlayersService, CreatePlayerService createPlayerService,
                            GetPlayerService getPlayerService, GetPlayerCountServiceBean getPlayerCountService,
                            UpdatePlayerService updatePlayerService, DeletePlayerService deletePlayerService,
                            @Qualifier("createPlayerValidator") Validator createPlayerRequestValidator,
                            @Qualifier("playerInfoValidator") Validator playerInfoValidator) {
        this.getPlayersService = getPlayersService;
        this.createPlayerService = createPlayerService;
        this.getPlayerService = getPlayerService;
        this.getPlayerCountService = getPlayerCountService;
        this.updatePlayerService = updatePlayerService;
        this.deletePlayerService = deletePlayerService;
        this.createPlayerRequestValidator = createPlayerRequestValidator;
        this.playerInfoValidator = playerInfoValidator;
    }

    @InitBinder("createPlayerRequest")
    protected void initCraetePlayerBinder(WebDataBinder binder) {
        binder.setValidator(createPlayerRequestValidator);
    }

    @InitBinder("playerInfo")
    protected void initUpdatePlayerBinder(WebDataBinder binder) {
        binder.setValidator(playerInfoValidator);
    }

    @GetMapping(path = "/rest/players")
    @ResponseStatus(HttpStatus.OK)
        //в 2022 в запросе должен приходить Pageble и  возвращаться Page<PlayerInfo> но так тесты не проходят
        //почитай про pageble, его можно передавать как аргумент для сортировки в репозитории хибернейт сам разрулит
    ResponseEntity<List<PlayerInfo>> getPlayers(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String title,
                                                @RequestParam(required = false) Race race,
                                                @RequestParam(required = false) Profession profession,
                                                @RequestParam(required = false) Long after,
                                                @RequestParam(required = false) Long before,
                                                @RequestParam(required = false) Boolean banned,
                                                @RequestParam(required = false) Integer minExperience,
                                                @RequestParam(required = false) Integer maxExperience,
                                                @RequestParam(required = false) Integer minLevel,
                                                @RequestParam(required = false) Integer maxLevel,
                                                @RequestParam(required = false, defaultValue = "ID") PlayerOrder order,
                                                @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                @RequestParam(required = false, defaultValue = "3") Integer pageSize
    ) {
        return ResponseEntity.ok(getPlayersService.getPlayers(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel, order, pageNumber, pageSize));
    }

    @GetMapping(path = "/rest/players/count")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> getPlayersCount(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String title,
                                         @RequestParam(required = false) Race race,
                                         @RequestParam(required = false) Profession profession,
                                         @RequestParam(required = false) Long after,
                                         @RequestParam(required = false) Long before,
                                         @RequestParam(required = false) Boolean banned,
                                         @RequestParam(required = false) Integer minExperience,
                                         @RequestParam(required = false) Integer maxExperience,
                                         @RequestParam(required = false) Integer minLevel,
                                         @RequestParam(required = false) Integer maxLevel
    ) {
        return ResponseEntity.ok(getPlayerCountService.getPlayerCount(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel));
    }

    @PostMapping(path = "/rest/players")
    @ResponseStatus(HttpStatus.OK)
        //должно быть HttpStatus.CREATED - 201 но по условиям задачи 200
    ResponseEntity<PlayerInfo> createPlayerInfo(@Validated @RequestBody CreatePlayerRequest createPlayerRequest) {
        return ResponseEntity.ok(createPlayerService.create(createPlayerRequest));
    }

    @GetMapping(path = "/rest/players/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PlayerInfo> getPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(getPlayerService.getById(id));
    }

    @PostMapping(path = "/rest/players/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PlayerInfo> updatePlayerInfo(@PathVariable Long id, @Validated @RequestBody PlayerInfo playerInfo) {
        return ResponseEntity.ok(updatePlayerService.updateById(id, playerInfo));
    }

    @DeleteMapping(path = "/rest/players/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePlayerInfo(@PathVariable Long id) {
        deletePlayerService.deleteById(id);
    }
}

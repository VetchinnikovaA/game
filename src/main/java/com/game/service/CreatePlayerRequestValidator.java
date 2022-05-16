package com.game.service;

import com.game.entity.CreatePlayerRequest;
import com.game.entity.PlayerInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service("createPlayerValidator")
public class CreatePlayerRequestValidator implements Validator {

    private final Validator playerInfoValidator;

    public CreatePlayerRequestValidator(@Qualifier("playerInfoValidator") Validator playerInfoValidator) {
        this.playerInfoValidator = playerInfoValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreatePlayerRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PlayerInfo player = (PlayerInfo) o;

        if (player.getName() == null) {
            errors.rejectValue("name", "must be not null");
        }

        if (player.getProfession() == null) {
            errors.rejectValue("profession", "must be not null");
        }

        if (player.getTitle() == null) {
            errors.rejectValue("title", "must be not null");
        }

        if (player.getExperience() == null) {
            errors.rejectValue("experience", "must be not null");
        }

        if (player.getBirthday() == null) {
            errors.rejectValue("birthday", "must be not null");
        }

        if (player.getRace() == null) {
            errors.rejectValue("race", "value must be not null");
        }

        playerInfoValidator.validate(o, errors);
    }
}

package com.game.service;

import com.game.entity.PlayerInfo;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service("playerInfoValidator")
public class PlayerInfoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PlayerInfo.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PlayerInfo player = (PlayerInfo) o;

        if (player.getName() != null && (player.getName().isEmpty() || player.getName().length() > 12)) {
            errors.rejectValue("name", "length must be between 1 and 12");
        }

        if (player.getTitle() != null && player.getTitle().length() > 30) {
            errors.rejectValue("title", "length must be between 1 and 30");
        }

        if (player.getExperience() != null && (player.getExperience() < 0 || player.getExperience() > 10000000)) {
            errors.rejectValue("experience", "value must be between 0 and 10 000 000");
        }

        if (player.getBirthday() != null) {
            if (player.getBirthday() <= 0)
                errors.rejectValue("birthday", "value must be positive");
            else {
                LocalDate birthDay = Instant.ofEpochMilli(player.getBirthday()).atZone(ZoneId.systemDefault()).toLocalDate();
                if (birthDay.getYear() < 2000 || birthDay.getYear() > 3000) {
                    errors.rejectValue("birthday", "year must be between 2000 and 3000");
                }
            }
        }
    }
}

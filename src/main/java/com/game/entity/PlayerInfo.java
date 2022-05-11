package com.game.entity;

import java.time.*;

public class PlayerInfo {

    private Long id;

    private String name;

    private String title;

    private Race race;

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    private Profession profession;

    private Long birthday;

    private Boolean banned;

    private Integer experience;

    private Integer level;

    private Integer untilNextLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public Long getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public static PlayerInfo.Builder builder() {
        return new PlayerInfo().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public PlayerInfo.Builder id(Long id) {
            PlayerInfo.this.id = id;
            return this;
        }

        public PlayerInfo.Builder name(String name) {
            PlayerInfo.this.name = name;
            return this;
        }

        public PlayerInfo.Builder title(String title) {
            PlayerInfo.this.title = title;
            return this;
        }

        public PlayerInfo.Builder race(Race race) {
            PlayerInfo.this.race = race;
            return this;
        }

        public PlayerInfo.Builder profession(Profession profession) {
            PlayerInfo.this.profession = profession;
            return this;
        }

        public PlayerInfo.Builder birthday(ZonedDateTime birthday) {
            PlayerInfo.this.birthday = birthday.toInstant().toEpochMilli();
            return this;
        }

        public PlayerInfo.Builder experience(Integer experience) {
            PlayerInfo.this.experience = experience;
            return this;
        }

        public PlayerInfo.Builder banned(Boolean banned) {
            PlayerInfo.this.banned = banned;
            return this;
        }

        public PlayerInfo.Builder level(Integer level) {
            PlayerInfo.this.level = level;
            return this;
        }

        public PlayerInfo.Builder untilNextLevel(Integer untilNextLevel) {
            PlayerInfo.this.untilNextLevel = untilNextLevel;
            return this;
        }

        public PlayerInfo build() {
            return PlayerInfo.this;
        }
    }
}

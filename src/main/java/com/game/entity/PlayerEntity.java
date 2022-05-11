package com.game.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "player")
public class PlayerEntity {

    private PlayerEntity() {
    }

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12)
    private String name;

    @Column(length = 30)
    private String title;

    @Enumerated(EnumType.STRING)
    private Race race;

    @Enumerated(EnumType.STRING)
    private Profession profession;

    private ZonedDateTime birthday;

    @Column(nullable = false)
    private Boolean banned = false;

    private Integer experience;

    private Integer level;

    private Integer untilNextLevel;

    public static Builder builder() {
        return new PlayerEntity().new Builder();
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

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = Instant.ofEpochMilli(birthday).atZone(ZoneId.of("Europe/Moscow"));
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder name(String name) {
            PlayerEntity.this.name = name;
            return this;
        }

        public Builder title(String title) {
            PlayerEntity.this.title = title;
            return this;
        }

        public Builder race(Race race) {
            PlayerEntity.this.race = race;
            return this;
        }

        public Builder profession(Profession profession) {
            PlayerEntity.this.profession = profession;
            return this;
        }

        public Builder birthday(ZonedDateTime birthday) {
            PlayerEntity.this.birthday = birthday;
            return this;
        }

        public Builder birthday(Long birthday) {
            PlayerEntity.this.birthday = Instant.ofEpochMilli(birthday).atZone(ZoneId.of("Europe/Moscow"));
            return this;
        }

        public Builder experience(Integer experience) {
            PlayerEntity.this.experience = experience;
            return this;
        }

        public Builder banned(Boolean banned) {
            PlayerEntity.this.banned = banned;
            return this;
        }

        public Builder level(Integer level) {
            PlayerEntity.this.level = level;
            return this;
        }

        public Builder untilNextLevel(Integer untilNextLevel) {
            PlayerEntity.this.untilNextLevel = untilNextLevel;
            return this;
        }

        public PlayerEntity build() {
            return PlayerEntity.this;
        }
    }
}

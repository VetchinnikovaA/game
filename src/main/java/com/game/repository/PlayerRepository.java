package com.game.repository;

import com.game.entity.PlayerEntity;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZoneId;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long>, JpaSpecificationExecutor<PlayerEntity> {

    static Specification<PlayerEntity> getSpecification(String name, String title, Race race, Profession profession, Long after, Long before, Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel) {
        Specification<PlayerEntity> nameFilter = ((root, criteriaQuery, criteriaBuilder) -> name == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        Specification<PlayerEntity> titleFilter = ((root, criteriaQuery, criteriaBuilder) -> title == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        Specification<PlayerEntity> raceFilter = ((root, criteriaQuery, criteriaBuilder) -> race == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("race"), race));
        Specification<PlayerEntity> professionFilter = ((root, criteriaQuery, criteriaBuilder) -> profession == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("profession"), profession));
        Specification<PlayerEntity> bannedFilter = ((root, criteriaQuery, criteriaBuilder) -> banned == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("banned"), banned));
        Specification<PlayerEntity> afterFilter = ((root, criteriaQuery, criteriaBuilder) -> after == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), Instant.ofEpochMilli(after).atZone(ZoneId.of("Europe/Moscow"))));
        Specification<PlayerEntity> beforeFilter = ((root, criteriaQuery, criteriaBuilder) -> before == null ? criteriaBuilder.conjunction() : criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), Instant.ofEpochMilli(before).atZone(ZoneId.of("Europe/Moscow"))));
        Specification<PlayerEntity> experienceMinFilter = ((root, criteriaQuery, criteriaBuilder) -> minExperience == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThanOrEqualTo(root.get("experience"), minExperience));
        Specification<PlayerEntity> experienceMaxFilter = ((root, criteriaQuery, criteriaBuilder) -> maxExperience == null ? criteriaBuilder.conjunction() : criteriaBuilder.lessThanOrEqualTo(root.get("experience"), maxExperience));
        Specification<PlayerEntity> levelMinFilter = ((root, criteriaQuery, criteriaBuilder) -> minLevel == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThanOrEqualTo(root.get("level"), minLevel));
        Specification<PlayerEntity> levelMaxFilter = ((root, criteriaQuery, criteriaBuilder) -> maxLevel == null ? criteriaBuilder.conjunction() : criteriaBuilder.lessThanOrEqualTo(root.get("level"), maxLevel));
        return Specification.where(bannedFilter).and(nameFilter).and(titleFilter).and(raceFilter).and(afterFilter).and(beforeFilter).and(professionFilter).and(experienceMinFilter).and(experienceMaxFilter).and(levelMinFilter).and(levelMaxFilter);
    }
}

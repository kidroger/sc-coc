package me.shufork.biz.repository;

import me.shufork.biz.domain.CocClan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CocClanRepository extends JpaRepository<CocClan,String > {


    @Modifying
    @Query(value =
            "INSERT INTO t_clan(`f_tag`, `f_name`,`f_clan_level`, `f_badge_large`, `f_badge_medium`, `f_badge_small`, `z_version`) " +
                    " VALUES (:#{#entity.tag},:#{#entity.name},:#{#entity.clanLevel},:#{#entity.badgeLarge},:#{#entity.badgeMedium},:#{#entity.badgeSmall},0)" +
                    "ON DUPLICATE KEY UPDATE f_clan_level = :#{#entity.clanLevel},f_badge_large = :#{#entity.badgeLarge},f_badge_medium = :#{#entity.badgeMedium},f_badge_small = :#{#entity.badgeSmall},z_version = z_version + 1",
            nativeQuery = true)
    int insertOrUpdate(@Param("entity") CocClan entity);


    @Modifying
    @Query("update CocClan u set u.clanLevel= :#{#entity.clanLevel},u.badgeLarge = :#{#entity.badgeLarge},u.badgeMedium = :#{#entity.badgeMedium},u.badgeSmall = :#{#entity.badgeSmall} where u.tag = :#{#entity.tag} ")
    int updateClan(@Param("entity") CocClan entity);
}

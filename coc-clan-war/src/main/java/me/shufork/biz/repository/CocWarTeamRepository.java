package me.shufork.biz.repository;

import me.shufork.biz.domain.CocWarTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CocWarTeamRepository extends JpaRepository<CocWarTeam,String> {

    CocWarTeam findAllByClanAndWarTime(String clan,Date warTime);
    CocWarTeam findAllByOpponentAndWarTime(String opponent,Date warTime);

    Page<CocWarTeam> findAllByClanOrderByWarTimeDesc(String clan, Pageable pageable);
    Page<CocWarTeam> findAllByOpponentOrderByWarTimeDesc(String opponent, Pageable pageable);

    <T> T findOneById(Class<T> type,String id);

    @Modifying
    @Query("update CocWarTeam u set u.attacks = ?2 ,u.expEarned = ?3 where u.id = ?1 ")
    int updatePrivateInfo(String id,int attacks,int expEarned);

    @Modifying
    @Query(value =
            "INSERT INTO t_war_team(`f_id`, `f_name` , `f_opponent_team` , `f_stars` , `f_war_time`,`f_badge_large` , `f_badge_medium` , `f_badge_small` , `f_clan` , `f_clan_level` , `f_destruction_percentage` , `f_attacks` , `f_exp_earned` , `z_version` )" +
                    " VALUES (:#{#warTeam.id},:#{#warTeam.name},:#{#warTeam.opponent},:#{#warTeam.stars},:#{#warTeam.warTime},:#{#warTeam.badgeLarge},:#{#warTeam.badgeMedium},:#{#warTeam.badgeSmall},:#{#warTeam.clan},:#{#warTeam.clanLevel},:#{#warTeam.destructionPercentage},:#{#warTeam.attacks},:#{#warTeam.expEarned},0)" +
                    "ON DUPLICATE KEY UPDATE f_attacks = :#{#warTeam.attacks},f_exp_earned = :#{#warTeam.expEarned},z_version = z_version + 1",
            nativeQuery = true)
    int insertOrUpdate(@Param("warTeam") CocWarTeam warTeam);
}

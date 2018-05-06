package me.shufork.biz.repository;

import me.shufork.biz.domain.CocClanDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CocClanDetailsRepository extends JpaRepository<CocClanDetails,String> {
    Page<CocClanDetails> findAllByOrderByModifiedTimeDesc(Pageable pageable);

    @Modifying
    @Query(value =
            "INSERT INTO t_clan_details(`f_tag`, `f_clan_points`, `f_clan_versus_points`, `f_description`, `f_location_id`, `f_required_trophies`, `f_total_members`, `f_type`, `f_war_frequency`, `f_war_log_public`, `f_war_losses`, `f_war_ties`, `f_war_win_streak`, `f_war_wins`, `z_version`) " +
                    " VALUES (:#{#entity.tag},:#{#entity.clanPoints},:#{#entity.clanVersusPoints},:#{#entity.description},:#{#entity.location}," +
                    ":#{#entity.requiredTrophies},:#{#entity.totalMembers},:#{#entity.type},:#{#entity.warFrequency},:#{#entity.warLogPublic}," +
                    ":#{#entity.warLosses},:#{#entity.warTies},:#{#entity.warWinStreak},:#{#entity.warWins},0) " +
                    "ON DUPLICATE KEY UPDATE f_clan_points = :#{#entity.clanPoints},f_clan_versus_points = :#{#entity.clanVersusPoints},f_description = :#{#entity.description},f_location_id = :#{#entity.location}," +
                    "f_required_trophies = :#{#entity.requiredTrophies},f_total_members = :#{#entity.totalMembers},f_type = :#{#entity.type},f_war_frequency = :#{#entity.warFrequency},f_war_log_public = :#{#entity.warLogPublic}," +
                    "f_war_losses = :#{#entity.warLosses},f_war_ties = :#{#entity.warTies},f_war_win_streak = :#{#entity.warWinStreak},f_war_wins = :#{#entity.warWins},z_version = z_version + 1",
            nativeQuery = true)
    int insertOrUpdate(@Param("entity") CocClanDetails entity);
}

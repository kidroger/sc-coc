package me.shufork.biz.repository;

import me.shufork.biz.domain.CocWarLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CocWarLogRepository extends JpaRepository<CocWarLog,String> {
    Page<CocWarLog> findAllByHomeTeam(String homeTeam, Pageable pageable);
    Page<CocWarLog> findAllByAwayTeam(String awayTeam, Pageable pageable);

    @Modifying
    @Query(value =
            "INSERT INTO t_war_log(`f_id`, `f_end_time`, `f_home_team`, `f_away_team`, `f_owner`, `f_result`, `f_team_size`, `z_version`) " +
                    "VALUES (:#{#warLog.id},:#{#warLog.endTime},:#{#warLog.homeTeam},:#{#warLog.awayTeam},:#{#warLog.owner},:#{#warLog.result},:#{#warLog.teamSize}, 0)" +
                    "ON DUPLICATE KEY UPDATE z_version = z_version + 1",
            nativeQuery = true)
    int insertOrUpdate(@Param("warLog") CocWarLog warLog);
}

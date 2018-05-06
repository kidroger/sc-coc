package me.shufork.biz.repository;

import me.shufork.biz.domain.PlayerTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerTrackingRepository extends JpaRepository<PlayerTracking,String> {

    @Modifying
    @Query(value =
            "INSERT INTO t_player_tracking(`f_player_tag`, `f_name`, `f_score`, `f_last_hit_time`, `z_version`) " +
                    "VALUES (:#{#playerTracking.player},:#{#playerTracking.name},:#{#playerTracking.score},  NULL, 0)" +
                    "ON DUPLICATE KEY UPDATE z_version = z_version + 1",
            nativeQuery = true)
    int insertOrIgnore(@Param("playerTracking") PlayerTracking playerTracking);

    @Modifying
    @Query(value =
            "INSERT INTO t_player_tracking(`f_player_tag`, `f_name`, `f_score`, `f_last_hit_time`, `z_version`) " +
                    "VALUES (:#{#playerTracking.player}, :#{#playerTracking.name},:#{#playerTracking.score},  NULL, 0)" +
                    "ON DUPLICATE KEY UPDATE f_last_hit_time = :#{#playerTracking.lastHit},f_score = :#{#playerTracking.score} ,z_version = z_version + 1",
            nativeQuery = true)
    int insertOrUpdate(@Param("playerTracking") PlayerTracking playerTracking);
}

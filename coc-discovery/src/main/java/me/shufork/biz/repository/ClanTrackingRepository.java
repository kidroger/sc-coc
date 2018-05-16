package me.shufork.biz.repository;

import me.shufork.biz.domain.ClanTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Date;
import java.util.List;

public interface ClanTrackingRepository extends JpaRepository<ClanTracking,String > {

    ClanTracking findFirstByOrderByLastHitAscScoreDesc();

    <T> T findFirstByOrderByLastHitAscScoreDesc(Class<T> type);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <T> Page<T> findAllByOrderByLastHitAscScoreDesc(Class<T> type, Pageable pageable);

    @Modifying
    @Query("update ClanTracking u set u.lastHit = ?2 where u.clan = ?1 ")
    int updateLastHit(String clan,Date lastHit);

    <T> List<T> findAllByLastHit(Date lastHit,Class<T> type);

    @Modifying
    @Query(value =
            "INSERT INTO t_clan_tracking(`f_clan_tag`, `f_name`, `f_score`, `f_last_hit_time`, `z_version`) " +
                    "VALUES (:#{#clanTracking.clan}, :#{#clanTracking.name},:#{#clanTracking.score},:#{#clanTracking.lastHit}, 0)" +
                    "ON DUPLICATE KEY UPDATE z_version = z_version + 1",
            nativeQuery = true)
    int insertOrIgnore(@Param("clanTracking") ClanTracking clanTracking);

    @Modifying
    @Query(value =
            "INSERT INTO t_clan_tracking(`f_clan_tag`, `f_name`, `f_score`, `f_last_hit_time`, `z_version`) " +
                    "VALUES (:#{#clanTracking.clan}, :#{#clanTracking.name},:#{#clanTracking.score},:#{#clanTracking.lastHit},  0)" +
                    "ON DUPLICATE KEY UPDATE f_last_hit_time = :#{#clanTracking.lastHit},f_score = :#{#clanTracking.score} ,z_version = z_version + 1",
            nativeQuery = true)
    int insertOrUpdate(@Param("clanTracking") ClanTracking clanTracking);

}

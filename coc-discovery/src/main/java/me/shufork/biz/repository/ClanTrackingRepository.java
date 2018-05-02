package me.shufork.biz.repository;

import me.shufork.biz.domain.ClanTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ClanTrackingRepository extends JpaRepository<ClanTracking,String > {

    ClanTracking findFirstByOrderByLastHitAscScoreDesc();

    <T> T findFirstByOrderByLastHitAscScoreDesc(Class<T> type);

    @Modifying
    @Query("update ClanTracking u set u.lastHit = ?3 ,u.version = u.version + 1 where u.clan = ?1 and u.version = ?2")
    int updateLastHit(String clan,long version,Date lastHit);

    <T> List<T> findAllByLastHit(Date lastHit,Class<T> type);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = " UPDATE t_clan_tracking  SET f_last_hit_time = ?1, z_version = z_version + 1  ORDER BY f_last_hit_time ASC, f_score DESC  LIMIT ?2",nativeQuery = true)
    int markLastHit(Date lastHit,int size);
}

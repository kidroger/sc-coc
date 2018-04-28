package me.shufork.biz.repository;

import me.shufork.biz.domain.ClanTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ClanTrackingRepository extends JpaRepository<ClanTracking,String > {

    ClanTracking findFirstByOrderByLastHitAscScoreDesc();

    <T> T findFirstByOrderByLastHitAscScoreDesc(Class<T> type);

    @Modifying
    @Query("update ClanTracking u set u.lastHit = ?2 where u.clan = ?1")
    void updateLastHit(String clan,Date lastHit);
}

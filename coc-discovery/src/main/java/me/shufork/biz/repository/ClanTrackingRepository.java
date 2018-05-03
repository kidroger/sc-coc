package me.shufork.biz.repository;

import me.shufork.biz.domain.ClanTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

}

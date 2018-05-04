package me.shufork.biz.repository;

import me.shufork.biz.domain.CocWarTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
}

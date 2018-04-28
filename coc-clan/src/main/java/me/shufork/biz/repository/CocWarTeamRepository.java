package me.shufork.biz.repository;

import me.shufork.biz.domain.CocWarTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CocWarTeamRepository extends JpaRepository<CocWarTeam,String> {

    CocWarTeam findAllByClanAndWarTime(String clan,Date warTime);
    CocWarTeam findAllByOpponentAndWarTime(String opponent,Date warTime);

    Page<CocWarTeam> findAllByClanOrderByWarTimeDesc(String clan, Pageable pageable);
    Page<CocWarTeam> findAllByOpponentOrderByWarTimeDesc(String opponent, Pageable pageable);
}

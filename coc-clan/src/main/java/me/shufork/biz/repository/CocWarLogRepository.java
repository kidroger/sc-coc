package me.shufork.biz.repository;

import me.shufork.biz.domain.CocWarLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocWarLogRepository extends JpaRepository<CocWarLog,String> {
    Page<CocWarLog> findAllByHomeTeam(String homeTeam, Pageable pageable);
    Page<CocWarLog> findAllByAwayTeam(String awayTeam, Pageable pageable);
}

package me.shufork.biz.repository;

import me.shufork.biz.domain.CocPlayerLegendStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocPlayerLegendStatisticRepository extends JpaRepository<CocPlayerLegendStatistic,String> {
    CocPlayerLegendStatistic findOneByPlayer(String player);
}

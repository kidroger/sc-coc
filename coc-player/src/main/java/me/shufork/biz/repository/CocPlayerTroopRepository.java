package me.shufork.biz.repository;

import me.shufork.biz.domain.CocPlayerTroop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocPlayerTroopRepository extends JpaRepository<CocPlayerTroop,String>{
    List<CocPlayerTroop> findAllByPlayer(String player);
}

package me.shufork.biz.repository;

import me.shufork.biz.domain.CocPlayerHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocPlayerHeroRepository extends JpaRepository<CocPlayerHero,String> {
    List<CocPlayerHero> findAllByPlayer(String player);
}

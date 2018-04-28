package me.shufork.biz.repository;

import me.shufork.biz.domain.CocPlayerSpell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocPlayerSpellRepository extends JpaRepository<CocPlayerSpell,String> {
    List<CocPlayerSpell> findAllByPlayer(String player);
}

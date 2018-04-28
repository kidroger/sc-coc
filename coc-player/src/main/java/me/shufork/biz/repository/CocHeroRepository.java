package me.shufork.biz.repository;

import me.shufork.biz.domain.CocHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CocHeroRepository extends JpaRepository<CocHero,String> {
}

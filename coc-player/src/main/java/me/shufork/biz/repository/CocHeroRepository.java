package me.shufork.biz.repository;

import me.shufork.biz.domain.CocHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocHeroRepository extends JpaRepository<CocHero,String> {
}

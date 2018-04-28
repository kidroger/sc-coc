package me.shufork.biz.repository;

import me.shufork.biz.domain.PlayerTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTrackingRepository extends JpaRepository<PlayerTracking,String> {
}

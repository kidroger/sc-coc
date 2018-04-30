package me.shufork.biz.repository;

import me.shufork.biz.domain.CocClanDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocClanDetailsRepository extends JpaRepository<CocClanDetails,String> {
    Page<CocClanDetails> findAllByOrderByModifiedTimeDesc(Pageable pageable);
}

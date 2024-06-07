package com.progressoft.warehouse.repository;


import com.progressoft.warehouse.model.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(noRollbackFor = RuntimeException.class)
public interface DealRepository extends JpaRepository<Deal, Integer> {
    Long countByDealId(int id);
}

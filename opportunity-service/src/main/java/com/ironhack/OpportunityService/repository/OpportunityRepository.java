package com.ironhack.OpportunityService.repository;

import com.ironhack.OpportunityService.dao.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    //Find opportunity by id
    Optional<Opportunity> findById(Long id);

//    @Query("SELECT o.id, o.status, o.product, o.quantity FROM Opportunity o WHERE o.accountId = :id")
    List<Opportunity> findAllByAccountId(Long id);

}

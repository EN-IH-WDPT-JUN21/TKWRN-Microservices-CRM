package com.ironhack.leadservice.repository;

import com.ironhack.leadservice.dao.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

    Optional<Lead> findById(Long id);

    /*//Report Lead by SalesRep
    @Query("SELECT r.repName, COUNT(l) FROM Lead l RIGHT JOIN l.salesRep r GROUP BY r.repName ORDER BY r.repName")
    List<Object[]> findCountLeadByRepName();

    @Query("SELECT l.id, l.name FROM Lead l")
    List<Object[]> findAllLeads();*/


}

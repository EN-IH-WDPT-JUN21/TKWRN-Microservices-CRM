package com.ironhack.leadservice.repository;

import com.ironhack.leadservice.dao.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
  
    //Look up lead by id
    Optional<Lead> findById(Long id);

    //Report Lead by SalesRep
    @Query(value = "SELECT sales_rep_report.sales_rep_name, COUNT(*) FROM lead_report " +
            "RIGHT JOIN sales_rep_report ON lead_report.sales_rep_id " +
            "GROUP BY sales_rep_report.sales_rep_name ORDER BY sales_rep_report.sales_rep_name", nativeQuery = true)
    List<Object[]> findCountLeadByRepName();

    //Show leads
    @Query("SELECT l.id, l.name FROM Lead l")
    List<Object[]> findAllLeads();
}

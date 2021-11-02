package com.ironhack.ReportService.repository;

import com.ironhack.ReportService.dao.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Long> {
    @Query("SELECT r.id, r.repName FROM SalesRep r")
    List<Object[]> findAllSalesReps();
}

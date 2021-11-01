package com.ironhack.ReportService.repository;

import com.ironhack.ReportService.dao.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    //Report mean number employee count for all Accounts
    @Query("SELECT AVG(employeeCount) FROM Account")
    Optional<Double> findMeanEmployeeCount();

    //Show accounts
    @Query(value = "SELECT account.id, contact.companyName FROM Contact JOIN account ON contact.accountId = account.id", nativeQuery = true)
    List<Object[]> findAllAccounts();

    // *** Median Report is needed JPQL can give list of all employeecounts in an ordered int array, needs a second step to find the median from this ***
    @Query("SELECT employeeCount FROM Account order by employeeCount")
    int[]findMedianEmployeeCountStep1();

    //Report Maximum  employee count for all Accounts
    @Query("SELECT MAX(employeeCount) FROM Account")
    Optional<Integer> findMaxEmployeeCount();

    //Report Minimum  employee count for all Accounts
    @Query("SELECT MIN(employeeCount) FROM Account")
    Optional<Integer> findMinEmployeeCount();

}

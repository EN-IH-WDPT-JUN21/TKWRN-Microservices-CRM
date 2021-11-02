package com.ironhack.ReportService.repository;

import com.ironhack.ReportService.dao.Opportunity;
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

    //Report Opportunities by SalesRep
    @Query(value = "SELECT sales_rep_report.sales_rep_name, COUNT(*) FROM opportunity_report " +
            "RIGHT JOIN sales_rep_report ON opportunity_report.sales_rep_id " +
            "GROUP BY  sales_rep_report.sales_rep_name " +
            "ORDER BY  sales_rep_report.sales_rep_name", nativeQuery = true)
    List<Object[]> findCountOpportunityByRepName();

    //Show all opportunities
    @Query(value = "SELECT opportunity_report.id, opportunity_report.status, opportunity_report.product, " +
            "opportunity_report.quantity, contact_report.contact_name FROM opportunity_report " +
            "JOIN contact_report ON opportunity_report.decision_maker_id", nativeQuery = true)
    List<Object[]> findAllOpportunities();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by SalesRep (takes a parameter argument)
    @Query(value = "SELECT sales_rep_report.sales_rep_name, COUNT(*) FROM opportunity_report " +
            "RIGHT JOIN sales_rep_report ON opportunity_report.sales_rep_id WHERE opportunity_report.status = ?1 " +
            "GROUP BY sales_rep_report.sales_rep_name ORDER BY sales_rep_report.sales_rep_name", nativeQuery = true)
    List<Object[]> findCountOpportunityByRepNameForStatus(String status);

    //Report Opportunities by Product
    @Query("SELECT o.product, count(o) FROM Opportunity o GROUP BY o.product ORDER BY o.product")
    List<Object[]> findCountOppForProduct();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by Product (takes a parameter argument)
    @Query("SELECT o.product, COUNT(o) FROM Opportunity o WHERE o.status = :status GROUP BY o.product ORDER BY o.product")
    List<Object[]> findCountOpportunityByProductForStatus(@Param("status") Enum status);

    //Report Opportunities by Country
    @Query(value = "SELECT account_report.country, COUNT(*) FROM opportunity_report " +
            "JOIN account_report ON opportunity_report.account_id " +
            "GROUP BY account_report.country " +
            "ORDER BY account_report.country", nativeQuery = true)
    List<Object[]> findCountOppForCountry();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by Country (takes a parameter argument)
    @Query(value = "SELECT account_report.country, COUNT(*) FROM opportunity_report " +
            "JOIN account_report ON opportunity_report.account_id WHERE status = ?1  " +
            "GROUP BY account_report.country ORDER BY account_report.country", nativeQuery = true)
    List<Object[]> findCountOpportunityByCountryForStatus(String status);

    //Report Opportunities by City
    @Query(value = "SELECT account_report.city, COUNT(*) FROM opportunity_report " +
            "JOIN account_report ON opportunity_report.account_id " +
            "GROUP BY account_report.city ORDER BY account_report.city", nativeQuery = true)
    List<Object[]> findCountOppForCity();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by City (takes a parameter argument)
    @Query(value = "SELECT account_report.city, COUNT(*) FROM opportunity_report " +
            "JOIN account_report ON opportunity_report.account_id WHERE status = ?1 " +
            "GROUP BY account_report.city ORDER BY account_report.city", nativeQuery = true)
    List<Object[]> findCountOpportunityByCityForStatus(String status);

    //Report Opportunities by Industry
    @Query(value = "SELECT account_report.industry, COUNT(*) FROM opportunity_report " +
            "JOIN account_report ON opportunity_report.account_id " +
            "GROUP BY account_report.industry ORDER BY account_report.industry", nativeQuery = true)
    List<Object[]> findCountOppForIndustry();

    //Report CLOSED-WON, CLOSED-LOST, and OPEN opportunities by Industry (takes a parameter argument)
    @Query(value = "SELECT account_report.industry, COUNT(*) FROM opportunity_report " +
            "JOIN account_report ON opportunity_report.account_id WHERE status = ?1 " +
            "GROUP BY account_report.industry ORDER BY account_report.industry", nativeQuery = true)
    List<Object[]> findCountOpportunityByIndustryForStatus(String status);

    //Report mean number of products quantity for all Opportunities
    @Query("SELECT AVG(quantity) FROM Opportunity")
    Optional<Double> findMeanProductQuantity();

    // *** Median Report is needed JPQL can give list of all quantities in an ordered int array, needs a second step to find the median from this ***
    @Query("SELECT quantity FROM Opportunity order by quantity")
    int[]findMedianQuantityStep1();

    //Report Maximum  products quantity for all Opportunities
    @Query("SELECT MAX(quantity) FROM Opportunity")
    Optional<Integer> findMaxProductQuantity();

    //Report Minimum  products quantity for all Opportunities
    @Query("SELECT MIN(quantity) FROM Opportunity")
    Optional<Integer> findMinProductQuantity();

    //Report Mean number of Opportunities associated with an account
    @Query(value = "SELECT avg(a.count_opportunity) " +
            "FROM (SELECT count(DISTINCT id) AS count_opportunity FROM opportunity_report GROUP BY opportunity_report.account_id order by count_opportunity) a", nativeQuery = true)
    Optional<Double>findMeanOpportunitiesPerAccount();

    // *** Median Report is needed JPQL can give list of all opportunitycounts in an ordered int array, needs a second step to find the median from this ***
    @Query(value = "select count(distinct id) as count_opportunity from opportunity_report group by opportunity_report.account_id order by count_opportunity", nativeQuery = true)
    int[]findMedianOppsPerAccountStep1();

    //Report Max number of Opportunities associated with an account
    @Query(value = "select max(a.count_opportunity) from (select count(distinct id) as count_opportunity from opportunity group by account_id order by count_opportunity) a", nativeQuery = true)
    Optional<Double>findMaxOpportunitiesPerAccount();

    //Report Min number of Opportunities associated with an account
    @Query(value = "select min(a.count_opportunity) from (select count(distinct id) as count_opportunity from opportunity group by account_id order by count_opportunity) a", nativeQuery = true)
    Optional<Double>findMinOpportunitiesPerAccount();

}

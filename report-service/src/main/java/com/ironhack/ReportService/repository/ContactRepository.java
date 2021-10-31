package com.ironhack.ReportService.repository;

import com.ironhack.ReportService.dao.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,String> {

    @Query("SELECT c.id, c.name, c.companyName FROM Contact c")
    List<Object[]> findAllContacts();

    Optional<Contact> findById(Long id);


    Contact getById(Long decisionMakerId);
}

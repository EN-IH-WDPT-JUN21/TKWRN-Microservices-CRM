package com.ironhack.contactservice.repository;

import com.ironhack.contactservice.dao.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByAccountId(Long id);
}

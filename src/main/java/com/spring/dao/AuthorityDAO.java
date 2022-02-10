package com.spring.dao;

import com.spring.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDAO extends JpaRepository<Authority, Long> {
}

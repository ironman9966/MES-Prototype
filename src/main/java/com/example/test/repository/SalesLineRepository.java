package com.example.test.repository;

import com.example.test.SalesLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesLineRepository extends JpaRepository<SalesLine, Long> {
}

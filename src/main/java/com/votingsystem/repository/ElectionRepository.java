package com.votingsystem.repository;

import com.votingsystem.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionRepository extends JpaRepository<Election, Long> {
}

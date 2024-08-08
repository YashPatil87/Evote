package com.votingsystem.repository;

import com.votingsystem.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Voter findByUsername(String username);
}

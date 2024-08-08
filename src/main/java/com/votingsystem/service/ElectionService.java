package com.votingsystem.service;

import com.votingsystem.entity.Election;
import java.util.List;
import java.util.Optional;

public interface ElectionService {
    List<Election> findAll();
    Optional<Election> findById(Long id);
    void save(Election election);
    void deleteById(Long id);
}

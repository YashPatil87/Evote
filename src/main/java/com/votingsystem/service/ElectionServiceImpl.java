package com.votingsystem.service;

import com.votingsystem.entity.Election;
import com.votingsystem.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionServiceImpl implements ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    @Override
    public List<Election> findAll() {
        return electionRepository.findAll();
    }

    @Override
    public Optional<Election> findById(Long id) {
        return electionRepository.findById(id);
    }

    @Override
    public void save(Election election) {
        electionRepository.save(election);
    }

    @Override
    public void deleteById(Long id) {
        electionRepository.deleteById(id);
    }
}

package com.votingsystem.service;

import com.votingsystem.entity.Voter;
import com.votingsystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    public Voter registerVoter(Voter voter) {
        return voterRepository.save(voter);
    }

    public Voter findByUsername(String username) {
        return voterRepository.findByUsername(username);
    }

    public List<Voter> findAllVoters() {
        return voterRepository.findAll();
    }

    public Voter findVoterById(Long id) {
        return voterRepository.findById(id).orElse(null);
    }

    public void saveVoter(Voter voter) {
        voterRepository.save(voter);
    }

    public void deleteVoterById(Long id) {
        voterRepository.deleteById(id);
    }
}

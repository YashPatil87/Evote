package com.votingsystem.service;

import com.votingsystem.entity.Admin;
import com.votingsystem.entity.Voter;
import com.votingsystem.repository.AdminRepository;
import com.votingsystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private VoterRepository voterRepository;

    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public List<Voter> findAllVoters() {
        return voterRepository.findAll();
    }
}

package com.votingsystem.controller;

import com.votingsystem.entity.Admin;
import com.votingsystem.entity.Voter;
import com.votingsystem.service.AdminService;
import com.votingsystem.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private VoterService voterService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/adminlogin")
    public String showAdminLogin() {
        return "adminlogin";
    }

    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes redirectAttributes) {
        Admin admin = adminService.findByUsername(username);
        if (admin != null && password.equals(admin.getPassword())) {
            model.addAttribute("admin", admin);
            return "redirect:/admindashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/adminlogin";
        }
    }

    @GetMapping("/adminregister")
    public String showAdminRegister() {
        return "adminregister";
    }

    @PostMapping("/adminregister")
    public String registerAdmin(Admin admin, Model model) {
        adminService.registerAdmin(admin);
        return "redirect:/adminlogin";
    }

    @GetMapping("/admindashboard")
    public String adminDashboard(Model model) {
        List<Voter> voters = voterService.findAllVoters();
        model.addAttribute("voters", voters);
        return "admindashboard";
    }

    @GetMapping("/managevoters")
    public String manageVoters(Model model) {
        List<Voter> voters = voterService.findAllVoters();
        model.addAttribute("voters", voters);
        return "managevoters";
    }

    @GetMapping("/admin/addvoter")
    public String showAddVoterForm(Model model) {
        model.addAttribute("voter", new Voter());
        return "addvoter";
    }

    @PostMapping("/admin/addvoter")
    public String addVoter(@ModelAttribute Voter voter) {
        voterService.saveVoter(voter);
        return "redirect:/managevoters";
    }

    @GetMapping("/admin/editvoter")
    public String showEditVoterForm(@RequestParam Long id, Model model) {
        Voter voter = voterService.findVoterById(id);
        model.addAttribute("voter", voter);
        return "editvoter";
    }

    @PostMapping("/admin/editvoter")
    public String editVoter(@ModelAttribute Voter voter) {
        voterService.saveVoter(voter);
        return "redirect:/managevoters";
    }

    @GetMapping("/admin/deletevoter")
    public String deleteVoter(@RequestParam Long id) {
        voterService.deleteVoterById(id);
        return "redirect:/managevoters";
    }

    @GetMapping("/manageelections")
    public String manageElections() {
        // Add logic to manage elections
        return "manageelections";
    }

    @GetMapping("/adminlogout")
    public String adminLogout() {
        return "redirect:/";
    }
}

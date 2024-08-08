package com.votingsystem.controller;

import com.votingsystem.entity.Election;
import com.votingsystem.entity.Voter;
import com.votingsystem.service.ElectionService;
import com.votingsystem.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@SessionAttributes("voter") // Store voter in session
public class VoterController {

    @Autowired
    private VoterService voterService;

    @Autowired
    private ElectionService electionService;

    @GetMapping("/voterlogin")
    public String showVoterLogin() {
        return "voterlogin";
    }

    @PostMapping("/voterlogin")
    public String voterLogin(@RequestParam String username, @RequestParam String password, Model model, RedirectAttributes redirectAttributes) {
        Voter voter = voterService.findByUsername(username);
        if (voter != null && password.equals(voter.getPassword())) {
            model.addAttribute("voter", voter); // Store voter in session
            return "redirect:/voterdashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/voterlogin";
        }
    }

    @GetMapping("/voterregister")
    public String showVoterRegister() {
        return "voterregister";
    }

    @PostMapping("/voterregister")
    public String registerVoter(Voter voter, Model model) {
        voterService.registerVoter(voter);
        return "redirect:/voterlogin";
    }

    @GetMapping("/voterdashboard")
    public String voterDashboard(Model model, @ModelAttribute("voter") Voter voter) {
        List<Election> elections = electionService.findAll(); // Fetch all elections

        model.addAttribute("elections", elections);
        return "voterdashboard";
    }

    @GetMapping("/viewelections")
    public String viewElections(Model model) {
        List<Election> elections = electionService.findAll();
        model.addAttribute("elections", elections);
        return "viewelections";
    }

    @GetMapping("/viewresults")
    public String viewResults(Model model) {
        // Implement logic to show election results
        return "viewresults";
    }

    @GetMapping("/voterlogout")
    public String voterLogout() {
        return "redirect:/";
    }
}

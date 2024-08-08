package com.votingsystem.controller;

import com.votingsystem.entity.Election;
import com.votingsystem.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @GetMapping
    public String listElections(Model model) {
        model.addAttribute("elections", electionService.findAll());
        return "manageelections";
    }

    @PostMapping("/add")
    public String addElection(Election election, RedirectAttributes redirectAttributes) {
        electionService.save(election);
        redirectAttributes.addFlashAttribute("message", "Election added successfully!");
        return "redirect:/elections";
    }

    @GetMapping("/edit")
    public String editElection(@RequestParam("id") Long id, Model model) {
        model.addAttribute("election", electionService.findById(id).orElse(null));
        return "editelection";
    }

    @PostMapping("/update")
    public String updateElection(Election election, RedirectAttributes redirectAttributes) {
        electionService.save(election);
        redirectAttributes.addFlashAttribute("message", "Election updated successfully!");
        return "redirect:/elections";
    }

    @GetMapping("/delete")
    public String deleteElection(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        electionService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Election deleted successfully!");
        return "redirect:/elections";
    }
}

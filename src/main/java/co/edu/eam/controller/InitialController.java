package co.edu.eam.controller;

import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Candidate;
import co.edu.eam.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@Slf4j
public class InitialController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var candidates = candidateService.list();
        model.addAttribute("candidates", candidates);
        return "index";
    }

    @GetMapping("/register")
    public String registrar(Model model){
        AppUser appUser = new AppUser();
        model.addAttribute("appUser", appUser);
        return "pages/register";
    }

}

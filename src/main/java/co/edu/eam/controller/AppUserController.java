package co.edu.eam.controller;

import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Candidate;
import co.edu.eam.service.AppUserService;
import co.edu.eam.service.CandidateService;
import co.edu.eam.utils.AuthDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    AuthDao authDao;

    @Autowired
    AppUserService appUserService;

    @Autowired
    CandidateService candidateService;

    @GetMapping("/")
    public String index(Model model){
        List<AppUser> appUsers = appUserService.list();
        model.addAttribute("appUsers", appUsers);
        return "pages/users";
    }

    @PostMapping("/register")
    public String register(@Valid AppUser appUser, Errors errors){
        if (errors.hasErrors()) {
            return "users/register";
        }
        appUserService.save(appUser);
        return "redirect:/login";
    }

    @PostMapping("/save")
    public String save(@Valid AppUser appUser, Errors errors){
        if (errors.hasErrors()) {
            return "users/save";
        }
        appUserService.save(appUser);
        return "redirect:/home";
    }

    @PostMapping("/vote/up/{id}")
    public String voteUp(@PathVariable("id")Long id){
        log.info("Voting up for: " + id +";");
        Candidate candidate = candidateService.findById(id);
        candidate.setVotesUp(candidate.getVotesUp()+1);
        AppUser currentUser = authDao.getAuthenticatedUser();
        currentUser.setCandidate(candidate);
        candidateService.save(candidate);
        appUserService.save(currentUser);
        return "redirect:/home";
    }

    @PostMapping("/vote/down/{id}")
    public String voteDown(@PathVariable("id")Long id){
        log.info("Voting down for: " + id +";");
        Candidate candidate = candidateService.findById(id);
        candidate.setVotesDown(candidate.getVotesDown()+1);
        AppUser currentUser = authDao.getAuthenticatedUser();
        currentUser.setCandidate(candidate);
        candidateService.save(candidate);
        appUserService.save(currentUser);
        return "redirect:/home";
    }
}

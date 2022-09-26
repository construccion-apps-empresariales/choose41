package co.edu.eam.controller;

import co.edu.eam.dao.AppUserDao;
import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Candidate;
import co.edu.eam.service.AppUserService;
import co.edu.eam.service.CandidateService;
import co.edu.eam.utils.Auth;
import co.edu.eam.utils.AuthDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@Slf4j
public class InitialController {

    @Autowired
    private AuthDao authDao;

    @Autowired
    private AppUserDao appUserDao;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        AppUser appUser = new AppUser();
        Candidate candidate = new Candidate();
        List<Candidate> candidates = candidateService.list();
        List<AppUser> users = appUserService.list();
        model.addAttribute("appUser", appUser);
        model.addAttribute("candidate", candidate);
        model.addAttribute("candidates", candidates);
        model.addAttribute("users", users);
        return "pages/home";
    }

    @GetMapping("/register")
    public String regiser(Model model){
        AppUser appUser = new AppUser();
        model.addAttribute("appUser", appUser);
        return "pages/register";
    }

    @ModelAttribute("currentUser")
    public AppUser setUserLoggedGlobally() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserDao.findByUsername(authentication.getName());
        return authDao.getAuthenticatedUser();
    }
}

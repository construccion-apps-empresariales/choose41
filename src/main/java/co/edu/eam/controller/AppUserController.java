package co.edu.eam.controller;

import co.edu.eam.domain.AppUser;
import co.edu.eam.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

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
}

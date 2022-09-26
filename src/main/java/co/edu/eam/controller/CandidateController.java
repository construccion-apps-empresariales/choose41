package co.edu.eam.controller;

import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Candidate;
import co.edu.eam.service.AppUserService;
import co.edu.eam.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    AppUserService appUserService;

    @Autowired
    CandidateService candidateService;

    @GetMapping("/")
    public String index(Model model){
        List<Candidate> candidates = candidateService.list();
        model.addAttribute("appUsers", candidates);
        return "pages/candidates";
    }

    @PostMapping("/save")
    public String save(@RequestParam("image") MultipartFile image,
                       @RequestParam("id") Optional<Long> id,
                       @RequestParam("title") String title,
                       @RequestParam("description") String description){
        Candidate candidate = new Candidate();
        candidate.setTitle(title);
        candidate.setDescription(description);
        try {
            if (image.isEmpty()) {
                candidate.setImage();
            } else {
                candidate.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
                candidate.setImageType(image.getContentType());
            }
        } catch (IOException e) {
            candidate.setImage();
            log.error(e.getMessage());
        }
        candidateService.save(candidate);
        return "redirect:/home";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") Long id,
                       @RequestParam("title") String title,
                       @RequestParam("description") String description){
        Candidate candidate = candidateService.findById(id);
        candidate.setId(id);
        candidate.setTitle(title);
        candidate.setDescription(description);
        candidateService.save(candidate);
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Candidate candidate = candidateService.findById(id);
        List<AppUser> users = candidateService.getVoters(candidate);
        for (AppUser user : users) {
            user.setCandidate(null);
            appUserService.save(user);
        }
        candidateService.delete(candidate);
        return "redirect:/home";
    }

    @GetMapping("/get/{id}")
    public String getCandidate(Model model, @PathVariable("id") Long id){
        Candidate candidate = candidateService.findById(id);
        model.addAttribute("candidate", candidate);
        return "/pages/edit-candidate";
    }
}

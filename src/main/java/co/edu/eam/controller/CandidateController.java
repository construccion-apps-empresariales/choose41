package co.edu.eam.controller;

import co.edu.eam.domain.Candidate;
import co.edu.eam.service.CandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Base64;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @GetMapping("/")
    public String index(Model model){
        List<Candidate> candidates = candidateService.list();
        model.addAttribute("appUsers", candidates);
        return "pages/candidates";
    }

    @PostMapping("/save")
    public String save(@Valid Candidate candidate, Errors errors){
        if (errors.hasErrors()) {
            return "candidates/save";
        }
        if (candidate.getImage() == null){
            candidate.setImage();
        }
        candidateService.save(candidate);
        return "redirect:/home";
    }

}

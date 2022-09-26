package co.edu.eam.service;

import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Candidate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateService {
    public List<Candidate> list();
    public Candidate findById(Long id);
    public void save(Candidate candidate);
    public void delete(Candidate candidate);
    public List<AppUser> getVoters(Candidate candidate);
}

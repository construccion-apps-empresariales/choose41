package co.edu.eam.service;

import co.edu.eam.dao.AppUserDao;
import co.edu.eam.dao.CandidateDao;
import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateServiceImplementation implements CandidateService {

    @Autowired
    private CandidateDao candidateDao;

    @Autowired
    private AppUserService appUserService;

    @Override
    @Transactional(readOnly = true)
    public List<Candidate> list() {
        return (List<Candidate>) candidateDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Candidate findById(Long id) {
        return candidateDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Candidate candidate) {
        candidateDao.save(candidate);
    }

    @Override
    @Transactional
    public void delete(Candidate candidate) {
        candidateDao.delete(candidate);
    }

    @Override
    public List<AppUser> getVoters(Candidate candidate) {
        ArrayList<AppUser> followers = new ArrayList<AppUser>() {
        };
        for (AppUser user: appUserService.list()) {
            if (user.getCandidate() == candidate){
                followers.add(user);
            }
        }
        return followers;
    }
}

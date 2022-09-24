package co.edu.eam.service;

import co.edu.eam.dao.CandidateDao;
import co.edu.eam.domain.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CandidateServiceImplementation implements CandidateService {

    @Autowired
    private CandidateDao candidateDao;

    @Override
    @Transactional(readOnly = true)
    public List<Candidate> list() {
        return (List<Candidate>) candidateDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Candidate findById(Candidate candidate) {
        return candidateDao.findById(candidate.getId()).orElse(null);
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
}

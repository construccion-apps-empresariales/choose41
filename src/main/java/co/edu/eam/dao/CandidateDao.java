package co.edu.eam.dao;

import co.edu.eam.domain.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateDao extends CrudRepository<Candidate, Long> {
}

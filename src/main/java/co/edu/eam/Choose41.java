package co.edu.eam;

import co.edu.eam.dao.AppUserDao;
import co.edu.eam.dao.RolDao;
import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Candidate;
import co.edu.eam.domain.Rol;
import co.edu.eam.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Choose41 {

	@Autowired
	private AppUserDao appUserDao;

	@Autowired
	private RolDao rolDao;

	@Autowired
	private CandidateService candidateService;

	public static void main(String[] args) {
		SpringApplication.run(Choose41.class, args);
	}

	@PostConstruct
	public void loadData(){
		createRoles();
		createUsers(0);
		createCandidates(0);
	}

	private void createRoles(){
		Rol adminRol = new Rol();
		adminRol.setId(1);
		adminRol.setName("ADMIN");
		rolDao.save(adminRol);

		Rol userRol = new Rol();
		userRol.setId(2);
		userRol.setName("USER");
		rolDao.save(userRol);
	}
	private void createUsers(int total){
		AppUser appAdmin = new AppUser();
		appAdmin.setId(1L);
		appAdmin.setUsername("admin");
		appAdmin.setPassword("admin123");
		appAdmin.setRol(rolDao.findById(1).get());
		appUserDao.save(appAdmin);

		AppUser appTester = appUserDao.findByUsername("tester");
		if (appTester == null) {
			appTester = new AppUser();
		}
		appTester.setUsername("tester");
		appTester.setPassword("tester123");
		appTester.setRol(rolDao.findById(2).get());
		appUserDao.save(appTester);

		for (int i = 0; i < total; i++) {
			AppUser generated = appUserDao.findByUsername("generated"+i);
			if (generated == null) {
				generated = new AppUser();
			}
			generated.setUsername("generated" + i);
			generated.setPassword("generated123");
			generated.setRol(rolDao.findById(2).get());
			appUserDao.save(generated);
		}
	}
	private void createCandidates(int total){
		for (int i = 1; i < total; i++) {
			Candidate candidate = candidateService.findById(Long.parseLong(""+i));
			if (candidate == null){
				candidate = new Candidate();
			}
			candidate.setTitle("generated candidate " + i);
			candidate.setDescription("Description for only having some kind of text, nothing special " + i);
			candidate.setImage();
			candidateService.save(candidate);
		}
	}
}

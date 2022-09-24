package co.edu.eam;

import co.edu.eam.dao.AppUserDao;
import co.edu.eam.dao.RolDao;
import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Rol;
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

	public static void main(String[] args) {
		SpringApplication.run(Choose41.class, args);
	}

	@PostConstruct
	public void loadData(){

		Rol adminRol = new Rol();
		adminRol.setId(1);
		adminRol.setName("ADMIN");
		rolDao.save(adminRol);

		Rol userRol = new Rol();
		userRol.setId(2);
		userRol.setName("USER");
		rolDao.save(userRol);

		AppUser appAdmin = new AppUser();
		appAdmin.setId(1L);
		appAdmin.setUsername("admin");
		appAdmin.setPassword("admin123");
		appAdmin.setRol(rolDao.findById(1).get());
		appUserDao.save(appAdmin);
	}
}
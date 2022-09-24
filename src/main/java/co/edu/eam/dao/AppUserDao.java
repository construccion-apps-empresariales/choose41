package co.edu.eam.dao;

import co.edu.eam.domain.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AppUserDao extends CrudRepository<AppUser, Long> {

    @Query("select u from AppUser u where u.username = ?1")
    public AppUser findByUsername(String username);
}

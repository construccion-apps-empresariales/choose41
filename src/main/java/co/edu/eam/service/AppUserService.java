package co.edu.eam.service;

import co.edu.eam.domain.AppUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppUserService {
    public List<AppUser> list();
    public AppUser findById(AppUser appUser);
    public void save(AppUser appUser);
    public void delete(AppUser appUser);
}

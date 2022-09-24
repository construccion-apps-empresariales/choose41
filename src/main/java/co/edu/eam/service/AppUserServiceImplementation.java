package co.edu.eam.service;

import co.edu.eam.dao.AppUserDao;
import co.edu.eam.domain.AppUser;
import co.edu.eam.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImplementation implements AppUserService {

    @Autowired
    private AppUserDao appUserDao;

    @Override
    @Transactional(readOnly = true)
    public List<AppUser> list() { return (List<AppUser>) appUserDao.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public AppUser findById(AppUser appUser) { return appUserDao.findById(appUser.getId()).orElse(null); }

    @Override
    @Transactional
    public void save(AppUser appUser) { appUserDao.save(appUser); }

    @Override
    @Transactional
    public void delete(AppUser appUser) { appUserDao.delete(appUser); }
}

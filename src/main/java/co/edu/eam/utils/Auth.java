package co.edu.eam.utils;

import co.edu.eam.dao.AppUserDao;
import co.edu.eam.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class Auth implements AuthDao{
    @Autowired
    AppUserDao appUserDao;
    @Override
    public AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserDao.findByUsername(authentication.getName());
        return appUser;
    }
}

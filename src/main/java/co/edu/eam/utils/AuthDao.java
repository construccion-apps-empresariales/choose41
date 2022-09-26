package co.edu.eam.utils;

import co.edu.eam.domain.AppUser;

public interface AuthDao {
    public AppUser getAuthenticatedUser();
}

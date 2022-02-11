package com.spring.security;

import com.spring.dao.UserDAO;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPricipalService implements UserDetailsService {

    private UserDAO userDAO;

    public UserPricipalService(){

    }
    @Autowired
    public UserPricipalService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
}

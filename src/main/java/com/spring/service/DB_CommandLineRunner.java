package com.spring.service;

import com.spring.dao.AuthorityDAO;
import com.spring.dao.RoleDAO;
import com.spring.dao.UserDAO;
import com.spring.model.Authority;
import com.spring.model.Role;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DB_CommandLineRunner implements CommandLineRunner {

    AuthorityDAO authorityDAO;
    UserDAO userDAO;
    RoleDAO roleDAO;


    @Autowired
    public DB_CommandLineRunner(AuthorityDAO authorityDAO, UserDAO userDAO, RoleDAO roleDAO) {
        this.authorityDAO = authorityDAO;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }




    @Override
    public void run(String... args) throws Exception {


        User user= new User("Hassan Askar","hassan","Hassan123#","via ponale",1);




        user.setRoles(this.roleDAO.findAll());
        user.setAuthorities(this.authorityDAO.findAll());
        for (Role role:user.getRoles()) {
            System.out.println(role.getRoleName());
        }

        for (Authority authority: user.getAuthorities()) {
            System.out.println(authority.getAuthorityName());
        }

        this.userDAO.save(user);
    }
}

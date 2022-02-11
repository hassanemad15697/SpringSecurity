package com.spring.service;

import com.spring.dao.AuthorityDAO;
import com.spring.dao.RoleDAO;
import com.spring.dao.UserDAO;
import com.spring.model.Authority;
import com.spring.model.Role;
import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DB_CommandLineRunner implements CommandLineRunner {

    AuthorityDAO authorityDAO;
    UserDAO userDAO;
    RoleDAO roleDAO;
    PasswordEncoder passwordEncoder;


    @Autowired
    public DB_CommandLineRunner(AuthorityDAO authorityDAO, UserDAO userDAO, RoleDAO roleDAO,PasswordEncoder passwordEncoder) {
        this.authorityDAO = authorityDAO;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder=passwordEncoder;
    }




    @Override
    public void run(String... args) throws Exception {


//        User admin= new User("Hassan Askar","hassan",passwordEncoder.encode("Hassan123#"),"via ponale",1);
//        admin.setRoles(this.roleDAO.findAll());
//        admin.setAuthorities(this.authorityDAO.findAll());
//        this.userDAO.save(admin);
//
//        User manager= new User("Amro Askar","amro",passwordEncoder.encode("Amro123#"),"sharkia",1);
//        List<Role> managerRole = new ArrayList<>();
//        managerRole.add(this.roleDAO.findById(2L).get());
//        managerRole.add(this.roleDAO.findById(3L).get());
//        managerRole.add(this.roleDAO.findById(4L).get());
//        manager.setRoles(managerRole);
//        List<Authority> authorities = new ArrayList<>();
//        authorities.add(this.authorityDAO.findById(2L).get());
//        authorities.add(this.authorityDAO.findById(3L).get());
//        manager.setAuthorities(authorities);
//        this.userDAO.save(manager);
//
//        User empolyee= new User("Israa Emad","israa",passwordEncoder.encode("Israa123#"),"milano",1);
//        List<Role> empolyeeRole = new ArrayList<>();
//        empolyeeRole.add(this.roleDAO.findById(3L).get());
//        empolyeeRole.add(this.roleDAO.findById(4L).get());
//        empolyee.setRoles(empolyeeRole);
//        List<Authority> empolyeeAuthorities = new ArrayList<>();
//        empolyeeAuthorities.add(this.authorityDAO.findById(2L).get());
//        empolyeeAuthorities.add(this.authorityDAO.findById(3L).get());
//        empolyee.setAuthorities(empolyeeAuthorities);
//        this.userDAO.save(empolyee);
//
//        User user= new User("Hager Emad","hager",passwordEncoder.encode("Hager123#"),"milano",1);
//        List<Role> userRole = new ArrayList<>();
//        userRole.add(this.roleDAO.findById(4L).get());
//        user.setRoles(userRole);
//        List<Authority> userAuthorities = new ArrayList<>();
//        userAuthorities.add(this.authorityDAO.findById(3L).get());
//        user.setAuthorities(userAuthorities);
//        this.userDAO.save(user);
//

    }
}

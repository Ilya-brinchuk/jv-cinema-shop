package com.cinema.controller;

import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.service.RoleService;
import com.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public DataInitializer(RoleService roleService,
                      UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role roleUser = new Role();
        roleUser.setRoleName("USER");
        Role roleAdmin = new Role();
        roleAdmin.setRoleName("ADMIN");
        roleService.add(roleUser);
        roleService.add(roleAdmin);
        User userAdmin = new User();
        userAdmin.setEmail("ilya@gmail.com");
        userAdmin.setPassword("2323");
        userAdmin.setRoles(Set.of(roleAdmin));
        userService.add(userAdmin);
    }
}

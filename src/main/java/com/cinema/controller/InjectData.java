package com.cinema.controller;

import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.service.RoleService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InjectData {
    private RoleService roleService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public InjectData(RoleService roleService,
                      UserService userService,
                      ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
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
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleAdmin);
        userAdmin.setRoles(roles);
        userService.add(userAdmin);
        shoppingCartService.registerNewShoppingCart(userAdmin);

    }
}

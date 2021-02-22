package com.cinema.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.service.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetails implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetails(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Can't find user by this email: " + email));
        Set<String> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }
        UserBuilder userBuilder = withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(roles.toArray(new String[0]));
        return userBuilder.build();
    }
}

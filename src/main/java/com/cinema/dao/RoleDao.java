package com.cinema.dao;

import com.cinema.model.Role;
import java.util.Optional;

public interface RoleDao {
    void add(Role role);

    Optional<Role> getRoleByName(String roleName);
}

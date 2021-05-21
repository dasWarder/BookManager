package com.babichev.bookmanager.repository.role;

import com.babichev.bookmanager.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Role add(Role role);

    Role get(int roleId);

    void remove(int roleId);

    Optional<Role> getByName(String name);
}

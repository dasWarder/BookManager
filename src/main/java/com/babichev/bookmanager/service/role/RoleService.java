package com.babichev.bookmanager.service.role;

import com.babichev.bookmanager.entity.Role;

import java.util.Optional;

public interface RoleService {

    Role add(Role role);

    Role get(int roleId);

    void remove(int roleId);

    Role getByName(String name);
}

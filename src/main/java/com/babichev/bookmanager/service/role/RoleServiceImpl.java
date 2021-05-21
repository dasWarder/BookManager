package com.babichev.bookmanager.service.role;

import com.babichev.bookmanager.entity.Role;
import com.babichev.bookmanager.exception.RoleNotFoundException;
import com.babichev.bookmanager.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        Assert.notNull(role, "Role couldn't be null");
        Role stored = roleRepository.add(role);

        return stored;
    }

    @Override
    public Role get(int roleId) {
        Role role = roleRepository.get(roleId);

        if (role == null) {
            throw new RoleNotFoundException(
                    String.format("Role with id=%d doesn't exist!", roleId));
        }

        return role;
    }

    @Override
    public void remove(int roleId) {
        roleRepository.remove(roleId);
    }

    @Override
    public Role getByName(String name) {
        Optional<Role> byName = roleRepository.getByName(name);

        if(!byName.isPresent()) {
            throw new RoleNotFoundException(
                    String.format("Role with name=%s not found!", name));
        }

        Role role = byName.get();

        return role;
    }
}

package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Role;
import com.babichev.bookmanager.repository.role.RoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.babichev.bookmanager.data.TestData.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = {"/db/clearDb.sql", "/db/populateDb.sql"})
class RoleRepositoryJpaTest {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void add() {
        Role role = createRole();
        Role added = roleRepository.add(role);
        role.setId(added.getId());

        Assert.assertEquals(added, role);
    }

    @Test
    public void update() {
        Role roleFromDb = roleRepository.get(USER_ROLE.getId());
        int id = roleFromDb.getId();
        Role updatedRole = updateRole(roleFromDb);
        Role updatedFromDb = roleRepository.add(updatedRole);
        updatedRole.setId(id);

        Assert.assertEquals(roleFromDb, updatedFromDb);
    }


    @Test
    public void getById() {
        Role roleFromDb = roleRepository.get(USER_ROLE.getId());

        Assert.assertEquals(roleFromDb, USER_ROLE);
    }

    @Test
    public void getByName() {
        String name = USER_ROLE.getName();
        Optional<Role> fromDbByName =
                roleRepository.getByName(name);

        Role role = fromDbByName.get();

        Assert.assertEquals(role.getName(), USER_ROLE.getName());
        Assert.assertEquals(role, USER_ROLE);
    }


    @Test
    public void remove() {
        Role roleFromDb = roleRepository.get(USER_ROLE.getId());

        Assert.assertEquals(roleFromDb, USER_ROLE);

        roleRepository.remove(roleFromDb.getId());

        Assert.assertEquals(null, roleRepository.get(roleFromDb.getId()));
    }
}
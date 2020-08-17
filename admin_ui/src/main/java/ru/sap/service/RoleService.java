package ru.sap.service;


import ru.sap.database.model.Role;

import java.util.List;

public interface RoleService {

    Role findRoleById(Long id);
    List<Role> findAll();
    void save(Role role);
    void deleteRoleById(Long id);

}

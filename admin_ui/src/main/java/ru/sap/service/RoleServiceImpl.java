package ru.sap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sap.database.model.Role;
import ru.sap.database.repo.RoleRepo;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }
}

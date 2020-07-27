package ru.sap.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sap.database.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
}

package ru.sap.database.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sap.database.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}

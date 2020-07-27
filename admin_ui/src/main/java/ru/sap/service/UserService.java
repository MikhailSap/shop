package ru.sap.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sap.database.model.User;


import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void saveUser(User user, boolean isUpdate);

    List<User> findAll();

    Optional<User> findUserById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);
}

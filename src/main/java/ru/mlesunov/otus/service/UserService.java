package ru.mlesunov.otus.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mlesunov.otus.entity.User;
import ru.mlesunov.otus.openapi.model.UserRegisterPostRequest;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findById(String id);
    List<User> searchUsers(String firstName, String secondName);
    User saveUser(UserRegisterPostRequest userRegisterPostRequest);
}

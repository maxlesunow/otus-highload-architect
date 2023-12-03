package ru.mlesunov.otus.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mlesunov.otus.entity.User;
import ru.mlesunov.otus.openapi.model.UserRegisterPostRequest;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findById(String id);

    User saveUser(UserRegisterPostRequest userRegisterPostRequest);
}

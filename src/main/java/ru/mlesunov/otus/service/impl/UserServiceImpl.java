package ru.mlesunov.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.entity.User;
import ru.mlesunov.otus.openapi.model.UserRegisterPostRequest;
import ru.mlesunov.otus.service.UserService;
import ru.mlesunov.otus.storage.dao.user.UserDaoImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;
    @Override
    public Optional<User> findById(String id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> searchUsers(String firstName, String secondName) {
        return userDao.getUsersByFirstNameAndSecondName(firstName, secondName);
    }

    @Override
    public User saveUser(UserRegisterPostRequest userRegisterPostRequest) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User()
                .setId(UUID.randomUUID())
                .setFirstName(userRegisterPostRequest.getFirstName())
                .setSecondName(userRegisterPostRequest.getSecondName())
                .setBirthdate(userRegisterPostRequest.getBirthdate())
                .setBiography(userRegisterPostRequest.getBiography())
                .setCity(userRegisterPostRequest.getCity())
                .setPassword(encoder.encode(userRegisterPostRequest.getPassword()));
        userDao.saveUser(user);

        return user;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = findById(username);

        return userDetail
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}

package ru.mlesunov.otus.storage.dao.user;

import ru.mlesunov.otus.entity.User;

import java.util.Optional;

public interface UserDao {

    void saveUser(User user);

    Optional<User> getUserById(String id);
}

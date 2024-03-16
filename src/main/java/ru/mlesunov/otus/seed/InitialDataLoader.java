package ru.mlesunov.otus.seed;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.config.properties.SeedProperties;
import ru.mlesunov.otus.entity.Friend;
import ru.mlesunov.otus.entity.User;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final SeedProperties seedProperties;
    @Override
    public void run(String... args) {
        if(seedProperties.getEnabled()){

            // Создаем пользователей
            Faker faker = new Faker(new Locale("ru-RU"));
            final List<User> users = new ArrayList<>(seedProperties.getUsersCount());

            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode("test");

            for (int i = 0; i < seedProperties.getUsersCount(); i++) {
                User user = new User();
                user.setId(UUID.randomUUID());
                user.setFirstName(faker.name().firstName());
                user.setSecondName(faker.name().lastName());
                user.setPassword(password);
                users.add(user);
            }

            // Добавляем пользователей в таблицу
            seedUsersTable(users);

            // Добавляем рандомное количество друзей для каждого пользователя
            users.forEach(user -> setFriends(user.getId(), users, new Random().nextInt(seedProperties.getMaxFriendsCount()-1)));

        }
    }

    private void seedUsersTable(List<User> users){

        jdbcTemplate.batchUpdate("insert into users(id, first_name, second_name, password) " +
                        "VALUES (?, ?, ?, ?)",
                users,
                100,
                (PreparedStatement ps, User user) -> {
                    ps.setObject(1, user.getId(), Types.OTHER);
                    ps.setString(2, user.getFirstName());
                    ps.setString(3, user.getSecondName());
                    ps.setString(4, user.getPassword());
                });
    }

    private void setFriends(UUID userId, List<User> users, int countFriends){

        final List<Friend> friends = new ArrayList<>(countFriends);
        for (int i = 0; i < countFriends; i++) {
            Friend friend = new Friend();
            friend.setUserId(userId);
            friend.setFriendId(users.stream().filter(u -> u.getId() != userId).toList().get(new Random().nextInt(users.size()-1)).getId());
            friends.add(friend);
        }

        jdbcTemplate.batchUpdate("insert into friends(user_id, friend_user_id) " +
                        "VALUES (?, ?)",
                friends,
                100,
                (PreparedStatement ps, Friend friend) -> {
                    ps.setObject(1, friend.getUserId(), Types.OTHER);
                    ps.setObject(2, friend.getFriendId(), Types.OTHER);
                });
    }
}
package ru.mlesunov.otus.seed;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.config.properties.SeedProperties;
import ru.mlesunov.otus.entity.User;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static ru.mlesunov.otus.storage.dao.user.UserSqlScriptStorage.*;

@Service
@RequiredArgsConstructor
public class InitialDataLoader implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final SeedProperties seedProperties;
    @Override
    public void run(String... args) {
        if(seedProperties.getEnabled()){
            seedUsersTable(seedProperties.getUsersCount());
        }
    }

    private void seedUsersTable(int count){

        Faker faker = new Faker(new Locale("ru-RU"));
        final List<User> users = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(UUID.randomUUID());
            user.setFirstName(faker.name().firstName());
            user.setSecondName(faker.name().lastName());
            user.setPassword(UUID.randomUUID().toString());
            users.add(user);
        }


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
}

package ru.mlesunov.otus.storage.dao.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mlesunov.otus.entity.User;
import ru.mlesunov.otus.storage.dao.user.mapper.UserRowMapper;

import java.sql.Types;
import java.util.Optional;

import static ru.mlesunov.otus.storage.dao.user.UserSqlScriptStorage.GET_USER_BY_ID;
import static ru.mlesunov.otus.storage.dao.user.UserSqlScriptStorage.INSERT_USER;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserDaoImpl implements UserDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void saveUser(User user) {
        var parameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId(), Types.OTHER)
                .addValue("firstName", user.getFirstName())
                .addValue("secondName", user.getFirstName())
                .addValue("birthdate", user.getBirthdate())
                .addValue("biography", user.getFirstName())
                .addValue("city", user.getFirstName())
                .addValue("password", user.getPassword());
        namedParameterJdbcTemplate.update(INSERT_USER, parameterSource);
    }

    @Override
    public Optional<User> getUserById(String id) {

        try {
            var parameterSource = new MapSqlParameterSource()
                    .addValue("id", id, Types.OTHER);
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(GET_USER_BY_ID, parameterSource, new UserRowMapper())
            );
        } catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
    }
}

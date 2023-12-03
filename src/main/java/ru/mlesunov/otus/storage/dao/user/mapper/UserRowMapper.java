package ru.mlesunov.otus.storage.dao.user.mapper;

import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;
import ru.mlesunov.otus.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new User()
                .setId(rs.getObject("id", UUID.class))
                .setFirstName(rs.getString("first_name"))
                .setSecondName(rs.getString("second_name"))
                .setBirthdate(rs.getDate("birthdate") == null ? null : rs.getDate("birthdate").toLocalDate())
                .setBiography(rs.getString("biography"))
                .setCity(rs.getString("city"))
                .setPassword(rs.getString("password"));
    }
}

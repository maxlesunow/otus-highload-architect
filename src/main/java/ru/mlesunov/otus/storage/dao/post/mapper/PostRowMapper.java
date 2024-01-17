package ru.mlesunov.otus.storage.dao.post.mapper;

import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;
import ru.mlesunov.otus.entity.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return new Post()
                .setId(rs.getObject("id", UUID.class))
                .setText(rs.getString("text"))
                .setAuthorUserId(rs.getObject("author_user_id", UUID.class));
    }
}

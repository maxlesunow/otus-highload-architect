package ru.mlesunov.otus.storage.dao.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mlesunov.otus.entity.Post;
import ru.mlesunov.otus.storage.dao.post.mapper.PostRowMapper;

import java.sql.Types;
import java.util.Optional;

import static ru.mlesunov.otus.storage.dao.post.PostSqlScriptStorage.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class PostDaoImpl implements PostDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void savePost(Post post) {
        var parameterSource = new MapSqlParameterSource()
                .addValue("id", post.getId(), Types.OTHER)
                .addValue("text", post.getText())
                .addValue("author_user_id", post.getAuthorUserId());
        namedParameterJdbcTemplate.update(INSERT_POST, parameterSource);
    }

    @Override
    public void updatePost(Post post) {
        var parameterSource = new MapSqlParameterSource()
                .addValue("id", post.getId(), Types.OTHER)
                .addValue("text", post.getText());
        namedParameterJdbcTemplate.update(UPDATE_POST, parameterSource);
    }

    @Override
    public void deletePost(Post post) {
        var parameterSource = new MapSqlParameterSource()
                .addValue("id", post.getId(), Types.OTHER);
        namedParameterJdbcTemplate.update(DELETE_POST, parameterSource);
    }

    @Override
    public Optional<Post> getPostById(String id) {

        try {
            var parameterSource = new MapSqlParameterSource()
                    .addValue("id", id, Types.OTHER);
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(GET_POST_BY_ID, parameterSource, new PostRowMapper())
            );
        } catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Post> getPostByIdAndAuthorUserId(String id, String authorUserId) {
        try {
            var parameterSource = new MapSqlParameterSource()
                    .addValue("id", id, Types.OTHER)
                    .addValue("author_user_id", authorUserId, Types.OTHER);
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(GET_POST_BY_ID_AND_AUTHOR_USER_ID, parameterSource, new PostRowMapper())
            );
        } catch (EmptyResultDataAccessException exception){
            return Optional.empty();
        }
    }

}

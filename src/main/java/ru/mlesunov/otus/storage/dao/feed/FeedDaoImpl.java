package ru.mlesunov.otus.storage.dao.feed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mlesunov.otus.entity.Post;

import java.sql.Types;

import static ru.mlesunov.otus.storage.dao.feed.FeedSqlScriptStorage.INSERT_FEED_BY_POST;


@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class FeedDaoImpl implements FeedDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void saveFeedByPost(Post post) {
        var parameterSource = new MapSqlParameterSource()
                .addValue("post_id", post.getId(), Types.OTHER)
                .addValue("post_author_user_id", post.getAuthorUserId(), Types.OTHER)
                .addValue("post_created_at", post.getCreatedAt());
        namedParameterJdbcTemplate.update(INSERT_FEED_BY_POST, parameterSource);
    }
}

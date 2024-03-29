package ru.mlesunov.otus.storage.dao.friend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mlesunov.otus.entity.Friend;

import java.sql.Types;
import java.util.List;

import static ru.mlesunov.otus.storage.dao.friend.FriendSqlScriptStorage.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class FriendDaoImpl implements FriendDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<String> getFriendIdsByUserId(String userId) {

        var parameterSource = new MapSqlParameterSource()
                .addValue("userId", userId, Types.OTHER);

        return namedParameterJdbcTemplate.queryForList(
                GET_FRIEND_IDS_BY_USER_ID,
                parameterSource,
                String.class);
    }

    @Override
    public void saveFriend(Friend friend) {
        var parameterSource = new MapSqlParameterSource()
                .addValue("user_id", friend.getUserId())
                .addValue("friend_user_id", friend.getFriendId());
        namedParameterJdbcTemplate.update(INSERT_FRIEND, parameterSource);
    }

    @Override
    public void deleteFriendByUserId(String userId, String friendUserId) {
        var parameterSource = new MapSqlParameterSource()
                .addValue("user_id", userId, Types.OTHER)
                .addValue("friend_user_id", friendUserId, Types.OTHER);
        namedParameterJdbcTemplate.update(DELETE_FRIEND_BY_USER_ID, parameterSource);
    }
}

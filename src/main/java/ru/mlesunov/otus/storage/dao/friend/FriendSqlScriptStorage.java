package ru.mlesunov.otus.storage.dao.friend;

import lombok.experimental.UtilityClass;

import static ru.mlesunov.otus.utils.SqlUtilsReader.loadResource;

@UtilityClass
public class FriendSqlScriptStorage {
    static final String INSERT_FRIEND = loadResource("sql/friend/insertFriend.sql");
    static final String DELETE_FRIEND_BY_USER_ID = loadResource("sql/friend/deleteFriendByUserId.sql");
}

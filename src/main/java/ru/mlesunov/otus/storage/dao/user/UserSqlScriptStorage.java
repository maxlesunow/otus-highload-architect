package ru.mlesunov.otus.storage.dao.user;

import lombok.experimental.UtilityClass;

import static ru.mlesunov.otus.utils.SqlUtilsReader.loadResource;

@UtilityClass
public class UserSqlScriptStorage {
    static final String INSERT_USER = loadResource("sql/user/insertUser.sql");
    static final String GET_USER_BY_ID = loadResource("sql/user/getUserById.sql");
}

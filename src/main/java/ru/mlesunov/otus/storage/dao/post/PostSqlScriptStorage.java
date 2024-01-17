package ru.mlesunov.otus.storage.dao.post;

import lombok.experimental.UtilityClass;

import static ru.mlesunov.otus.utils.SqlUtilsReader.loadResource;

@UtilityClass
public class PostSqlScriptStorage {
    static final String INSERT_POST = loadResource("sql/post/insertPost.sql");
    static final String UPDATE_POST = loadResource("sql/post/updatePost.sql");
    static final String DELETE_POST = loadResource("sql/post/deletePost.sql");
    static final String GET_POST_BY_ID = loadResource("sql/post/getPostById.sql");
    static final String GET_POST_BY_ID_AND_AUTHOR_USER_ID = loadResource("sql/post/getPostByIdAndAuthorUserId.sql");
}

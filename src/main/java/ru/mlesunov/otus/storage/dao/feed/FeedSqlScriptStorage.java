package ru.mlesunov.otus.storage.dao.feed;

import lombok.experimental.UtilityClass;

import static ru.mlesunov.otus.utils.SqlUtilsReader.loadResource;

@UtilityClass
public class FeedSqlScriptStorage {
    static final String INSERT_FEED_BY_POST = loadResource("sql/feed/insertFeedByPost.sql");
}

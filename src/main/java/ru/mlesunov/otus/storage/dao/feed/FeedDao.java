package ru.mlesunov.otus.storage.dao.feed;

import ru.mlesunov.otus.entity.Post;

public interface FeedDao {

    void saveFeedByPost(Post post);
}

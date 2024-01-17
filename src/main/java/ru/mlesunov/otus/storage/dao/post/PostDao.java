package ru.mlesunov.otus.storage.dao.post;

import ru.mlesunov.otus.entity.Post;

import java.util.Optional;

public interface PostDao {

    void savePost(Post post);
    void updatePost(Post post);
    void deletePost(Post post);
    Optional<Post> getPostById(String id);
    Optional<Post> getPostByIdAndAuthorUserId(String id, String authorUserId);
}

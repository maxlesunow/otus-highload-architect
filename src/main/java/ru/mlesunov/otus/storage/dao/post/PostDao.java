package ru.mlesunov.otus.storage.dao.post;

import ru.mlesunov.otus.entity.Post;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PostDao {

    void savePost(Post post);
    void updatePost(Post post);
    void deletePost(Post post);
    Optional<Post> getPostById(String id);
    Optional<Post> getPostByIdAndAuthorUserId(String id, String authorUserId);
    List<Post> getFriendsPostsByUserId(String userId, BigDecimal offset, BigDecimal limit);

}

package ru.mlesunov.otus.service;

import ru.mlesunov.otus.entity.Post;
import ru.mlesunov.otus.openapi.model.PostCreatePostRequest;
import ru.mlesunov.otus.openapi.model.PostUpdatePutRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> findById(String id);
    Optional<Post> findByIdForCurrentUser(String id);
    Post createPost(PostCreatePostRequest postCreatePostRequest);
    void updatePost(Post post, PostUpdatePutRequest postUpdatePutRequest);
    void deletePost(Post post);
    List<Post> getFriendsPostsByUserId(BigDecimal offset, BigDecimal limit);
}

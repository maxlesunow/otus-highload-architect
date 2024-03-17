package ru.mlesunov.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.entity.Post;
import ru.mlesunov.otus.openapi.model.PostCreatePostRequest;
import ru.mlesunov.otus.openapi.model.PostUpdatePutRequest;
import ru.mlesunov.otus.queue.feed.RabbitMQProducer;
import ru.mlesunov.otus.service.PostService;
import ru.mlesunov.otus.storage.dao.post.PostDaoImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDaoImpl postDao;
    private final RabbitMQProducer rabbitMQProducer;

    public Optional<Post> findById(String id) {
        return postDao.getPostById(id);
    }

    public Optional<Post> findByIdForCurrentUser(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return postDao.getPostByIdAndAuthorUserId(id, authentication.getName());
    }

    @Override
    public Post createPost(PostCreatePostRequest postCreatePostRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Post post = new Post()
                .setId(UUID.randomUUID())
                .setText(postCreatePostRequest.getText())
                .setAuthorUserId(UUID.fromString(authentication.getName()))
                .setCreatedAt(LocalDateTime.now());
        postDao.savePost(post);

        // Кидаем в очередь для формирования ленты
        rabbitMQProducer.sendMessage(post.getId().toString());
        return post;
    }

    @Override
    public void updatePost(Post post, PostUpdatePutRequest postUpdatePutRequest) {
        post.setText(postUpdatePutRequest.getText());
        postDao.updatePost(post);
    }

    @Override
    public void deletePost(Post post) {
        postDao.deletePost(post);
    }

    @Override
    public List<Post> getFriendsPostsByUserId(BigDecimal offset, BigDecimal limit) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return postDao.getFriendsPostsByUserId(authentication.getName(), offset, limit);
    }

}

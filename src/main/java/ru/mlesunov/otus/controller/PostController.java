package ru.mlesunov.otus.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.mlesunov.otus.entity.Post;
import ru.mlesunov.otus.openapi.api.PostApi;
import ru.mlesunov.otus.openapi.model.PostCreatePostRequest;
import ru.mlesunov.otus.openapi.model.PostUpdatePutRequest;
import ru.mlesunov.otus.service.PostService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class PostController implements PostApi {

    private final PostService postService;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> postCreatePost(PostCreatePostRequest postCreatePostRequest) {

        Post post = postService.createPost(postCreatePostRequest);
        return ResponseEntity.ok(post.getId().toString());
    }

    @Override
    public ResponseEntity<ru.mlesunov.otus.openapi.model.Post> postGetIdGet(String id) {
        var post = postService.findById(id);
        if(post.isPresent()){
            return ResponseEntity.ok(modelMapper.map(post, ru.mlesunov.otus.openapi.model.Post.class));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> postUpdatePut(PostUpdatePutRequest postUpdatePutRequest) {

        var post = postService.findById(postUpdatePutRequest.getId());
        if(post.isPresent()){
            postService.updatePost(post.get(), postUpdatePutRequest);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> postDeleteIdPut(String id) {
        var post = postService.findByIdForCurrentUser(id);
        if(post.isPresent()){
            postService.deletePost(post.get());
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<ru.mlesunov.otus.openapi.model.Post>> postFeedGet(BigDecimal offset, BigDecimal limit) {

        List<Post> posts = postService.getFriendsPostsByUserId(offset, limit);

        List<ru.mlesunov.otus.openapi.model.Post> postsDto = posts
                .stream()
                .map(post -> modelMapper.map(post, ru.mlesunov.otus.openapi.model.Post.class))
                .toList();

        return ResponseEntity.ok(postsDto);
    }
}

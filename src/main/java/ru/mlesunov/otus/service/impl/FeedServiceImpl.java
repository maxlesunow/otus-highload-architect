package ru.mlesunov.otus.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.entity.Post;
import ru.mlesunov.otus.service.FeedService;
import ru.mlesunov.otus.service.PostService;
import ru.mlesunov.otus.storage.dao.feed.FeedDaoImpl;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {

    private final FeedDaoImpl feedDao;
    private final PostService postService;

    @Override
    public void addNewPostToFeedByPostId(String postId) {

        Optional<Post> post = postService.findById(postId);

        if(post.isPresent()){
            feedDao.saveFeedByPost(post.get());
        }
        else {
            log.warn("Post not found! postId: " + postId);
        }
    }
}

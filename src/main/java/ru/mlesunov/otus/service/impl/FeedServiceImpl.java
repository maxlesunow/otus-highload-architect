package ru.mlesunov.otus.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.cache.feed.FeedCachedService;
import ru.mlesunov.otus.entity.Post;
import ru.mlesunov.otus.entity.UserFeed;
import ru.mlesunov.otus.service.FeedService;
import ru.mlesunov.otus.service.FriendService;
import ru.mlesunov.otus.service.PostService;
import ru.mlesunov.otus.storage.dao.feed.FeedDaoImpl;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {

    private final FeedCachedService feedCachedService;
    private final PostService postService;
    private final FriendService friendService;

    @Override
    public void addNewPostToFeedByPostId(String postId) {

        Optional<Post> post = postService.findById(postId);

        if(post.isPresent()){
            List<String> friendIds = friendService.getFriendIdsByUserId(post.get().getAuthorUserId().toString());
            friendIds.forEach(friend -> addPostToFeed(friend, post.get()));
        }
        else {
            log.warn("Post not found! postId: " + postId);
        }
    }

    private void addPostToFeed(String userId, Post post){

        // Получить ленту из кеша
//        UserFeed feed = feedCachedService.getUserFeedByUserId(userId);

            // Добавить пост с учетом сортировки
    }

    private void getFeedByUserId(){
        // Получить ленту из кеша

        // Если ленты нет, то получить из БД и сохранить в кеш
    }
}

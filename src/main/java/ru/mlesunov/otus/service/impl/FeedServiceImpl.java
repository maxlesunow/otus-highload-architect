package ru.mlesunov.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.entity.Post;
import ru.mlesunov.otus.service.FeedService;
import ru.mlesunov.otus.storage.dao.feed.FeedDaoImpl;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedDaoImpl feedDao;

    @Override
    public void addNewPostToFeed(Post post) {

        // Для всех подписчиков добавляем новую запись в таблицу фидов
        feedDao.saveFeedByPost(post);
    }
}

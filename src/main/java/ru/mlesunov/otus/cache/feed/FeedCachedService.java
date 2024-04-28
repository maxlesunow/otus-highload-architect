package ru.mlesunov.otus.cache.feed;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.entity.UserFeed;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedCachedService {

    private final String EMPLOYEE_CACHE = "EMPLOYEE";

    public void addPostToLenta(Long userId, WallPost wallPost) {
        Cache cache = cacheManager.getCache(FEED_CACHE);
        Assert.notNull(cache, LENTA_CACHE + " cache is null");
//
//        UserLentaDto userLentaDto = cache.get(userId, UserLentaDto.class);

        UserLentaDto userLentaDto = wallPostService.getLentaCached(userId);

        if (userLentaDto == null) {
            userLentaDto = new UserLentaDto();
            userLentaDto.setUserId(userId);
        }

        List<WallPostDto> wallPosts = userLentaDto.getWallPosts();
        Optional<Long> first = wallPosts.stream()
                .map(WallPostDto::getId)
                .filter(wallPost.getId()::equals)
                .findFirst();
        if (first.isPresent()) {
            log.warn("Post {} already in cache", wallPost);
            return;
        }

        wallPosts.add(wallPostToDtoConverter.convert(wallPost));
        wallPosts.sort((w1, w2) -> w2.getDateCreated().compareTo(w1.getDateCreated()));

        cache.put(userId, userLentaDto);
        log.info("Post added to User({}) cache& Post: {}", userId, wallPost);
    }
}

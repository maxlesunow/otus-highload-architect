package ru.mlesunov.otus.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;
import java.util.List;

@Data
@Accessors (chain = true)
@RedisHash("posts_feeds")
public class UserFeed {

    @Id
    private UUID userId;
    private List<Post> posts;
}

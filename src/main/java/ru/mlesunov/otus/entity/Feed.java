package ru.mlesunov.otus.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors (chain = true)
public class Feed {

    private UUID userId;
    private UUID postId;
    private LocalDateTime postCreatedAt;
}

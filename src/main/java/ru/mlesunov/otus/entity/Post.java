package ru.mlesunov.otus.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors (chain = true)
public class Post  {

    private UUID id;
    private UUID authorUserId;
    private String text;
    private LocalDateTime createdAt;
}

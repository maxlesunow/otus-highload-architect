package ru.mlesunov.otus.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors (chain = true)
public class Post  {

    private UUID id;
    private String text;
    private UUID authorUserId;
}

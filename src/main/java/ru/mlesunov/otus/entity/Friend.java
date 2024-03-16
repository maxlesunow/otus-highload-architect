package ru.mlesunov.otus.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;

@Data
@Accessors (chain = true)
public class Friend {

    private UUID userId;
    private UUID friendId;
}

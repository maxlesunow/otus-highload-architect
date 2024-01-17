package ru.mlesunov.otus.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Data
@Accessors (chain = true)
public class Friend {

    private UUID id;
    private UUID userId;
    private UUID friendId;
}

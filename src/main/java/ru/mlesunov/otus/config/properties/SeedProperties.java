package ru.mlesunov.otus.config.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "seed")
@Getter
@Setter
public class SeedProperties {
    private Boolean enabled;
    private Integer usersCount;
}

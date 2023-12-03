package ru.mlesunov.otus.utils;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class SqlUtilsReader {
    public static String loadResource(String file){
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(
                SqlUtilsReader.class.getClassLoader().getResourceAsStream(file)),
                StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));
    }
}

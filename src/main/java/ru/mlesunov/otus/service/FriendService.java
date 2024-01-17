package ru.mlesunov.otus.service;

import ru.mlesunov.otus.entity.Friend;
import ru.mlesunov.otus.entity.Post;
import ru.mlesunov.otus.openapi.model.PostCreatePostRequest;
import ru.mlesunov.otus.openapi.model.PostUpdatePutRequest;

import java.awt.*;
import java.util.Optional;

public interface FriendService {

    void setFriend(String friendUserId);
    void deleteFriend(String friendUserId);
}

package ru.mlesunov.otus.service;

import java.util.List;

public interface FriendService {

    List<String> getFriendIdsByUserId(String userId);
    void setFriend(String friendUserId);
    void deleteFriend(String friendUserId);
}

package ru.mlesunov.otus.storage.dao.friend;

import ru.mlesunov.otus.entity.Friend;

import java.util.List;

public interface FriendDao {

    List<String> getFriendIdsByUserId(String userId);

    void saveFriend(Friend friend);
    void deleteFriendByUserId(String userId, String friendUserId);
}

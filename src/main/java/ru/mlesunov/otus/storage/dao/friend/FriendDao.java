package ru.mlesunov.otus.storage.dao.friend;

import ru.mlesunov.otus.entity.Friend;

import java.util.Optional;

public interface FriendDao {

    void saveFriend(Friend friend);
    void deleteFriendByUserId(String userId, String friendUserId);
}

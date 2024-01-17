package ru.mlesunov.otus.storage.dao.friend;

import ru.mlesunov.otus.entity.Friend;

public interface FriendDao {

    void saveFriend(Friend friend);
    void deleteFriendByUserId(String userId, String friendUserId);
}

package ru.mlesunov.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.entity.Friend;
import ru.mlesunov.otus.service.FriendService;
import ru.mlesunov.otus.storage.dao.friend.FriendDao;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendDao friendDao;

    @Override
    public void setFriend(String friendUserId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Friend post = new Friend()
                .setUserId(UUID.fromString(authentication.getName()))
                .setFriendId(UUID.fromString(friendUserId));
        friendDao.saveFriend(post);
    }

    @Override
    public void deleteFriend(String friendUserId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        friendDao.deleteFriendByUserId(authentication.getName(), friendUserId);
    }

}

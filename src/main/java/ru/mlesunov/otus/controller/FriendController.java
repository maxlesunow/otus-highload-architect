package ru.mlesunov.otus.controller;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.mlesunov.otus.entity.User;
import ru.mlesunov.otus.openapi.api.FriendApi;
import ru.mlesunov.otus.openapi.api.UserApi;
import ru.mlesunov.otus.openapi.model.UserRegisterPost200Response;
import ru.mlesunov.otus.openapi.model.UserRegisterPostRequest;
import ru.mlesunov.otus.service.FriendService;
import ru.mlesunov.otus.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class FriendController implements FriendApi {

    private final FriendService friendService;

    @Override
    public ResponseEntity<Void> friendSetUserIdPut(String userId) {

        friendService.setFriend(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> friendDeleteUserIdPut(String userId) {

        friendService.deleteFriend(userId);
        return ResponseEntity.ok().build();
    }
}

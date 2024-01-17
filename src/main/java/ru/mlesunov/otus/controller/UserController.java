package ru.mlesunov.otus.controller;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.mlesunov.otus.entity.User;
import ru.mlesunov.otus.openapi.api.UserApi;
import ru.mlesunov.otus.openapi.model.UserRegisterPost200Response;
import ru.mlesunov.otus.openapi.model.UserRegisterPostRequest;
import ru.mlesunov.otus.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ru.mlesunov.otus.openapi.model.User> userGetIdGet(String id) {

        var user = userService.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(modelMapper.map(user, ru.mlesunov.otus.openapi.model.User.class));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<UserRegisterPost200Response> userRegisterPost(UserRegisterPostRequest userRegisterPostRequest) {

        User user = userService.saveUser(userRegisterPostRequest);

        UserRegisterPost200Response response = new UserRegisterPost200Response();
        response.setUserId(user.getId().toString());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ru.mlesunov.otus.openapi.model.User>> userSearchGet(String firstName, String secondName){

        List<User> users = userService.searchUsers(firstName, secondName);

        List<ru.mlesunov.otus.openapi.model.User> usersDto = users
                .stream()
                .map(user -> modelMapper.map(user, ru.mlesunov.otus.openapi.model.User.class))
                .toList();

        return ResponseEntity.ok(usersDto);
    }
}

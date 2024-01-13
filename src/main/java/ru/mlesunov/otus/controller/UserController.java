package ru.mlesunov.otus.controller;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import ru.mlesunov.otus.entity.User;
import ru.mlesunov.otus.openapi.api.DefaultApi;
import ru.mlesunov.otus.openapi.model.LoginPost200Response;
import ru.mlesunov.otus.openapi.model.LoginPostRequest;
import ru.mlesunov.otus.openapi.model.UserRegisterPost200Response;
import ru.mlesunov.otus.openapi.model.UserRegisterPostRequest;
import ru.mlesunov.otus.security.jwt.JwtService;
import ru.mlesunov.otus.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController implements DefaultApi {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<LoginPost200Response> loginPost(LoginPostRequest loginPostRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginPostRequest.getId(), loginPostRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginPostRequest.getId());

            LoginPost200Response loginPost200Response = new LoginPost200Response();
            loginPost200Response.token(token);

            return ResponseEntity.ok(loginPost200Response);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

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

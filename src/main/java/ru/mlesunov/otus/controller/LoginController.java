package ru.mlesunov.otus.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import ru.mlesunov.otus.openapi.api.AuthApi;
import ru.mlesunov.otus.openapi.model.LoginPost200Response;
import ru.mlesunov.otus.openapi.model.LoginPostRequest;
import ru.mlesunov.otus.security.jwt.JwtService;

@RestController
@AllArgsConstructor
public class LoginController implements AuthApi {

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
}
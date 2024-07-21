package com.wallet.walletapp.controller;

import com.wallet.walletapp.model.dto.TokenRequest;
import com.wallet.walletapp.model.dto.TokenResponse;
import com.wallet.walletapp.model.dto.UserDto;
import com.wallet.walletapp.security.AuthService;
import com.wallet.walletapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<TokenResponse> registerUser(@Valid @RequestBody UserDto user) {
        log.info("user register requester: '{}'", user.getUsername());
        UserDto newUser = userService.saveUser(user);
        return new ResponseEntity<>(
                authService.createToken(newUser.getUsername()),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{username}")
    @PreAuthorize("principal.username == #username")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        log.info("user info requester: '{}'", username);
        UserDto user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> getLoginToken(@RequestBody TokenRequest request) {
        log.info("login token requester: '{}'", request.getUsername());
        return new ResponseEntity<>(
                authService.authenticateAndGetToken(request),
                HttpStatus.OK
        );
    }

}

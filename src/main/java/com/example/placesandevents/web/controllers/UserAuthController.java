package com.example.placesandevents.web.controllers;

import com.example.placesandevents.auth.dto.AuthRequestDTO;
import com.example.placesandevents.auth.dto.AuthResponseDTO;
import com.example.placesandevents.auth.service.AuthService;
import com.example.placesandevents.user.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserAuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO registerUser(@RequestBody UserRequestDTO request){
        return authService.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO loginUser(@RequestBody AuthRequestDTO request){
        return authService.verifyUser(request);
    }
}

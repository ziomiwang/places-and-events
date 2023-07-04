package com.example.placesandevents.auth.service;

import com.example.placesandevents.auth.dto.AuthRequestDTO;
import com.example.placesandevents.auth.dto.AuthResponseDTO;
import com.example.placesandevents.domain.user.User;
import com.example.placesandevents.domain.user.repository.UserRepository;
import com.example.placesandevents.exception.customexceptions.InvalidCredentialsException;
import com.example.placesandevents.exception.customexceptions.LoginAlreadyTakenException;
import com.example.placesandevents.security.model.AuthorizedUser;
import com.example.placesandevents.security.util.JWTUtil;
import com.example.placesandevents.user.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO registerUser(UserRequestDTO request) {
        if (userRepository.existsUserByLogin(request.getLogin())) {
            throw new LoginAlreadyTakenException();
        }
        return saveNewUser(request);
    }

    private AuthResponseDTO saveNewUser(UserRequestDTO request) {
        User user = User.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();
        User savedUser = userRepository.save(user);
        return AuthResponseDTO.builder()
                .token(jwtUtil.generateToken(new AuthorizedUser(savedUser)))
                .build();
    }

    public AuthResponseDTO verifyUser(AuthRequestDTO authRequest) {
        Optional<User> byLogin = userRepository.findByLogin(authRequest.getLogin());

        return byLogin
                .filter(foundUser -> passwordEncoder.matches(authRequest.getPassword(), foundUser.getPassword()))
                .map(user -> jwtUtil.generateToken(new AuthorizedUser(user)))
                .map(token -> AuthResponseDTO.builder().token(token).build())
                .orElseThrow(InvalidCredentialsException::new);
    }
}

package com.example.placesandevents.security;

import com.example.placesandevents.domain.user.repository.UserRepository;
import com.example.placesandevents.security.model.AuthorizedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(AuthorizedUser::new)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

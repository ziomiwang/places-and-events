package com.example.placesandevents.auth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {

    private String login;
    private String password;
}

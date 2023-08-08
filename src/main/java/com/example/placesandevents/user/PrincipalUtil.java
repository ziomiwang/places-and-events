package com.example.placesandevents.user;

import com.example.placesandevents.domain.user.User;
import com.example.placesandevents.security.model.AuthorizedUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

public class PrincipalUtil {

    public static User getUserFromPrincipal(Principal principal){
        AuthorizedUser authorizedUserFromPrincipal = getAuthorizedUserFromPrincipal(principal);
        return User.builder().id(authorizedUserFromPrincipal.getId()).build();
    }

    private static AuthorizedUser getAuthorizedUserFromPrincipal(Principal principal){
        return (AuthorizedUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }
}

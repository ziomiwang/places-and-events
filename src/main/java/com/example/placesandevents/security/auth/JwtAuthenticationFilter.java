package com.example.placesandevents.security.auth;

import java.io.IOException;
import java.util.Objects;

import com.example.placesandevents.security.util.JWTUtil;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JWTUtil jwtUtil;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
    @NonNull final HttpServletRequest request,
    @NonNull final HttpServletResponse response,
    @NonNull final FilterChain filterChain
  ) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String jwt = authHeader.substring(7);
    try {
      final String tokenSubject = jwtUtil.getSubject(jwt);
      if (Objects.nonNull(tokenSubject) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(tokenSubject);
        if (jwtUtil.isTokenValid(jwt, userDetails)) {
          final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
          );
          authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
          );
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }
    } catch (final UsernameNotFoundException ex) {
      log.error("User authentication error {}", ex.getLocalizedMessage());
    } catch (final JWTDecodeException | TokenExpiredException ex) {
      log.error("JWT error {}", ex.getLocalizedMessage());
    }
    filterChain.doFilter(request, response);
  }
}

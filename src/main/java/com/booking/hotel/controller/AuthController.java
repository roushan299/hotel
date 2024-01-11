package com.booking.hotel.controller;

import com.booking.hotel.dto.AuthResponse;
import com.booking.hotel.dto.JwtRequest;
import com.booking.hotel.dto.JwtResponse;
import com.booking.hotel.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        JwtResponse jwtResponse = authService.login(jwtRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RegisteredOAuth2AuthorizedClient("okta")OAuth2AuthorizedClient oAuth2AuthorizedClient, @AuthenticationPrincipal OidcUser user){
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(user.getEmail());
        authResponse.setAccessToken(oAuth2AuthorizedClient.getAccessToken().getTokenValue());
        authResponse.setExpiresAt(oAuth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond());

        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());

        authResponse.setAuthorities(authorities);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}

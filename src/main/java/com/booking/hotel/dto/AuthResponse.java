package com.booking.hotel.dto;

import lombok.*;

import java.util.Collection;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String userId;
    private String accessToken;
    private long expiresAt;
    private Collection<String> authorities;
}

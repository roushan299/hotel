package com.booking.hotel.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class UserRequest {

    @Getter
    @Setter
    private String username;

    @Setter
    @Getter
    private String password;
}

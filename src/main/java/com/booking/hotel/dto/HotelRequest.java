package com.booking.hotel.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class HotelRequest {
    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private Long rating;

    @Getter
    @Setter
    private String city;

}

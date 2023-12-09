package com.booking.hotel.model;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class Hotel {
    private String id;

    @Size(min = 3, max = 10)
    private String name;

    @Max(value = 10)
    @Min(value = 1)
    private Long rating;

    @Size(min = 4, max = 20 )
    private String city;

    public Hotel(String id, String name, Long rating, String city) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", city='" + city + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

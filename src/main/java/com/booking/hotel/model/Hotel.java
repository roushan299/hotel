package com.booking.hotel.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


//@Component
//@Scope("prototype")
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 10)
    private String name;

    @Column(name = "rating")
    @Max(value = 10)
    @Min(value = 1)
    private Long rating;

    @Column(name = "city")
    @Size(min = 4, max = 20 )
    private String city;

    public Hotel(Long id, String name, Long rating, String city) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.city = city;
    }

    public Hotel() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

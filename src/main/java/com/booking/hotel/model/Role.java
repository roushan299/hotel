package com.booking.hotel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name =  "id")
    @Getter
    private Long id;


    @Column(name = "role_name")
    @Getter
    @Setter
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    @JsonBackReference
    @Getter
    @Setter
    private Set<User> userList;

}

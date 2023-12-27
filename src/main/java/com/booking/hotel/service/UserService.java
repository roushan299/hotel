package com.booking.hotel.service;

import com.booking.hotel.dto.UserRequest;
import com.booking.hotel.model.User;
import com.booking.hotel.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequest userRequest){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword());
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User getUserById(Long id) {
        return this.userRepository.findById(id).get();
    }
}

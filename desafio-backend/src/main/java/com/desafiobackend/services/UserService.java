package com.desafiobackend.services;


import com.desafiobackend.domain.user.User;
import com.desafiobackend.domain.user.UserType;
import com.desafiobackend.dtos.UserDTO;
import com.desafiobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {

        if (sender.getUserType() == UserType.SHOPKEEPER) {

            throw new Exception("Merchant type users cannot make transactions.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {

            throw new Exception("Insufficient balance, transaction not carried out.");
        }
    }

    public User createUser(UserDTO data) {

        User newUser = new User(data);
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}

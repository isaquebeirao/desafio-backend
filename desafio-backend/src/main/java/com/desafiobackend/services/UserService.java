package com.desafiobackend.services;


import com.desafiobackend.domain.user.User;
import com.desafiobackend.domain.user.UserType;
import com.desafiobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

}

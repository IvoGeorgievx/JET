package com.example.jet.user;

import com.example.jet.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Autowired()
    public UserService(UserRepository userRepository, TransactionRepository transactionRepository) {

        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public UserDTO createUser(String username, String password) {
        User newUser = new User(username, password);
        User savedUser = userRepository.save(newUser);
        return new UserDTO(savedUser.getId().toString(), savedUser.getUsername());
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserDTO(user.getId().toString(), user.getUsername())).collect(Collectors.toList());
    }

//    public List<TransactionDTO> getUserTransactions() {
//
//    }
}

package com.example.jet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component()
public class UserService {

    private final UserRepository userRepository;

    @Autowired()
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
